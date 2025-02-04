package com.example.ocare.api.health.service;

import com.example.ocare.api.health.domain.repository.HealthInfoRedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private HealthInfoRedisRepository healthInfoRedisRepository;

    @InjectMocks
    private FileService fileService;

    @Test
    @DisplayName("json 파일 읽고 redis 저장")
    void readAndSaveFile() {
        fileService.loadJsonFile();

        verify(healthInfoRedisRepository, atLeastOnce()).saveAll(any());
    }
}