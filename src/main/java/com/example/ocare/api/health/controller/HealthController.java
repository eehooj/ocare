package com.example.ocare.api.health.controller;

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

    @PostMapping(URIUtil.HEALTH_INFO)
    public ResponseEntity<ResultResponse<Object>> createHealthInfo() {
        healthInfoService.createHealthInfo();

        return ResponseEntity.ok(new ResultResponse<>(HttpStatus.CREATED));
    }

    @GetMapping(URIUtil.HEALTH_INFO)
    public ResponseEntity<ResultResponse<Object>> getHealthInfo() {
        healthInfoService.downloadHealthInfo();

        return ResponseEntity.ok(new ResultResponse<>(HttpStatus.OK));
    }
}
