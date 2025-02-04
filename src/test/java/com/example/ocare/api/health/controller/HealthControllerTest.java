package com.example.ocare.api.health.controller;

import com.example.ocare.api.health.service.HealthInfoService;
import com.example.ocare.global.util.URIUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(HealthController.class)
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HealthInfoService healthInfoService;

    @Test
    @DisplayName("createHealthInfo 엔드 포인트 동작 테스트")
    void createHealthInfoEndPoint() throws Exception {
        mockMvc.perform(post(URIUtil.HEALTH_INFO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CREATED"));

        verify(healthInfoService, times(1)).createHealthInfo();
    }
}