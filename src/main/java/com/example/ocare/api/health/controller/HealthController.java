package com.example.ocare.api.health.controller;

import com.example.ocare.api.health.dto.response.HealthInfoStatsResponse;
import com.example.ocare.api.health.service.HealthInfoService;
import com.example.ocare.global.common.dto.ResultResponse;
import com.example.ocare.global.util.URIUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final HealthInfoService healthInfoService;

    /**
     * 건강 정보 저장
     */
    @PostMapping(URIUtil.HEALTH_INFO)
    public ResponseEntity<ResultResponse<Object>> createHealthInfo() {
        healthInfoService.createHealthInfo();

        return ResponseEntity.ok(new ResultResponse<>(HttpStatus.CREATED));
    }

    /**
     * 건강 정보 조회
     */
    @GetMapping(URIUtil.HEALTH_INFO)
    public ResponseEntity<ResultResponse<HealthInfoStatsResponse>> getHealthInfo() {
        return ResponseEntity.ok(new ResultResponse<>(HttpStatus.OK, healthInfoService.getHealthInfoStats()));
    }
}
