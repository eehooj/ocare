package com.example.ocare.api.health.service;

import com.example.ocare.api.health.domain.entity.HealthInfoRedis;
import com.example.ocare.api.health.domain.repository.HealthInfoRedisRepository;
import com.example.ocare.global.exception.CustomException;
import com.example.ocare.global.exception.enumerate.FileExceptionType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Transactional
@Service
@RequiredArgsConstructor
public class FileService {

    private final ObjectMapper objectMapper;
    private final HealthInfoRedisRepository healthInfoRedisRepository;

    /**
     * json 파일 읽기
     */
    public void loadJsonFile() {
        String[] filePaths = {"input/INPUT_DATA1.json", "input/INPUT_DATA2.json",
                "input/INPUT_DATA3.json", "input/INPUT_DATA4.json"};

        for (String filePath : filePaths) {
            Resource resource = new ClassPathResource(filePath);

            try (InputStream inputStream = resource.getInputStream()) {
                JsonNode jsonNode = objectMapper.readTree(inputStream);
                parseData(jsonNode);
            } catch (Exception e) {
                e.printStackTrace();

                throw new CustomException(FileExceptionType.FILE_READ_FAILED);
            }
        }
    }

    public void parseData(JsonNode jsonNode) {
        String recordKey = objectMapper.convertValue(jsonNode.path("recordkey"), String.class);
        JsonNode data = jsonNode.path("data").path("entries");

        // 데이터를 Redis 엔티티 리스트로 변환
        List<HealthInfoRedis> dataList = jsonToHealthInfoRedis(data, recordKey);

        // Redis 저장
        healthInfoRedisRepository.saveAll(dataList);
    }

    /**
     * json -> healthIndoRedis
     */
    private List<HealthInfoRedis> jsonToHealthInfoRedis(JsonNode entries, String recordKey) {
        return StreamSupport.stream(entries.spliterator(), false)
                .map(item -> {
                    HealthInfoRedis entity = null;
                    
                    try {
                        entity = objectMapper.treeToValue(item, HealthInfoRedis.class);
                        entity.setId(UUID.randomUUID().toString());
                        entity.setRecordKey(recordKey);
                    } catch (Exception e) {
                        e.printStackTrace();

                        throw new CustomException(FileExceptionType.FILE_READ_FAILED);
                    }

                    return entity;
                })
                .toList();
    }
}
