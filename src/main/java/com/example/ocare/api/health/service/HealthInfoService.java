package com.example.ocare.api.health.service;

import com.example.ocare.api.health.domain.entity.HealthInfo;
import com.example.ocare.api.health.domain.entity.HealthInfoRedis;
import com.example.ocare.api.health.domain.repository.HealthInfoRedisRepository;
import com.example.ocare.api.health.domain.repository.HealthInfoRepository;
import com.example.ocare.global.exception.CustomException;
import com.example.ocare.global.exception.enumerate.DataExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthInfoService {

    private final FileService fileService;
    private final HealthInfoRepository healthInfoRepository;
    private final HealthInfoRedisRepository healthInfoRedisRepository;

    // 데이터 저장
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
}
