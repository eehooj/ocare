package com.example.ocare.api.health.service;

import com.example.ocare.api.health.domain.entity.HealthInfoRedis;
import com.example.ocare.api.health.domain.repository.HealthInfoRedisRepository;
import com.example.ocare.api.health.domain.repository.HealthInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HealthInfoServiceTest {

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private HealthInfoRedisRepository healthInfoRedisRepository;

    @Mock
    private HealthInfoRepository healthInfoRepository;

    @Mock
    private FileService fileService;

    @InjectMocks
    private HealthInfoService healthInfoService;


    @Test
    @DisplayName("redis 데이터 entity 저장")
    void saveHealthInfo() {
        List<HealthInfoRedis> dataList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            HealthInfoRedis data = HealthInfoRedis
                    .builder()
                    .id(String.valueOf(i))
                    .steps(i)
                    .period(HealthInfoRedis.Period.builder().from("2024-11-15 06:00:00").to("2024-11-15 06:10:00").build())
                    .distance(HealthInfoRedis.Distance.builder().unit("km").value(0.00519).build())
                    .calories(HealthInfoRedis.Calories.builder().unit("kcal").value(0.25).build())
                    .recordKey("asdffggh" + i)
                    .build();

            dataList.add(data);
        }

        when(healthInfoRedisRepository.findAll()).thenReturn(dataList);

        healthInfoService.createHealthInfo();

        verify(healthInfoRepository, times(1)).saveAll(anyList());
        verify(healthInfoRedisRepository, times(1)).deleteAll();
    }

    @Test
    @DisplayName("Health info 데이터 조회")
    void getHealthInfo() {
        healthInfoService.getHealthInfoStats();

        verify(healthInfoRepository, times(1)).getHealthInfoStats("{%Y-%m-%d}");
    }
}