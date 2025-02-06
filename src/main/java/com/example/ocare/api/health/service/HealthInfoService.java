package com.example.ocare.api.health.service;

import com.example.ocare.api.health.domain.entity.HealthInfo;
import com.example.ocare.api.health.domain.entity.HealthInfoRedis;
import com.example.ocare.api.health.domain.repository.HealthInfoRedisRepository;
import com.example.ocare.api.health.domain.repository.HealthInfoRepository;
import com.example.ocare.api.health.dto.HealthInfoDto;
import com.example.ocare.api.health.dto.response.HealthInfoStatsResponse;
import com.example.ocare.global.common.excel.ExcelService;
import com.example.ocare.global.exception.CustomException;
import com.example.ocare.global.exception.enumerate.DataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class HealthInfoService {

    private final FileService fileService;
    private final ExcelService excelService;
    private final HealthInfoRepository healthInfoRepository;
    private final HealthInfoRedisRepository healthInfoRedisRepository;

    /**
     * 건강 정보 DB에 저장 (redis -> db)
     */
    @Transactional
    public void createHealthInfo() {
        // 파일 읽어 오기
        fileService.loadJsonFile();

        // 레디스에 저장된 데이터 가져오기
        List<HealthInfoRedis> redisDataList = healthInfoRedisRepository.findAll();

        if (redisDataList.isEmpty()) {
            throw new CustomException(DataExceptionType.DATA_NOT_FOUND);
        }

        List<HealthInfo> infoList = redisDataList.stream().map(HealthInfo::redisToEntity).toList();

        // 데이터 저장
        healthInfoRepository.saveAll(infoList);
        healthInfoRedisRepository.deleteAll();
    }

    /**
     * 건강 정보 조회
     */
    public HealthInfoStatsResponse getHealthInfoStats() {
        // DB 조회
        List<HealthInfoDto> dailyHealthInfoStats = healthInfoRepository.getHealthInfoStats("%Y-%m-%d");
        List<HealthInfoDto> monthHealthInfoStats = healthInfoRepository.getHealthInfoStats("%Y-%m");

        // 엑셀 만들기
        makeExcel(dailyHealthInfoStats, monthHealthInfoStats);

        return HealthInfoStatsResponse
                .builder()
                .dailyList(dailyHealthInfoStats)
                .monthList(monthHealthInfoStats)
                .build();
    }

    private void makeExcel(List<HealthInfoDto> dailyHealthInfoStats, List<HealthInfoDto> monthHealthInfoStats) {
        Map<String, List<HealthInfoDto>> sheetData = new HashMap<>();
        sheetData.put("Daily", dailyHealthInfoStats);
        sheetData.put("Monthly", monthHealthInfoStats);

        Map<String, String[]> headers = new HashMap<>();
        headers.put("Daily", new String[]{"Daily", "Steps", "calories", "distance", "recordkey"});
        headers.put("Monthly", new String[]{"Monthly", "Steps", "calories", "distance", "recordkey"});

        Map<String, String[]> fields = new HashMap<>();
        fields.put("Daily", new String[]{"date", "steps", "calories", "distance", "recordKey"});
        fields.put("Monthly", new String[]{"date", "steps", "calories", "distance", "recordKey"});

        String fileName = "health_info_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                + ".xlsx";

        excelService.exportExcel(fileName, sheetData, headers, fields);
    }
}
