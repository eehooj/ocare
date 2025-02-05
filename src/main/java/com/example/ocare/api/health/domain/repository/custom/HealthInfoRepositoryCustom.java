package com.example.ocare.api.health.domain.repository.custom;

import com.example.ocare.api.health.dto.HealthInfoDto;

import java.util.List;

public interface HealthInfoRepositoryCustom {

    List<HealthInfoDto> getHealthInfoStats(String format);
}

