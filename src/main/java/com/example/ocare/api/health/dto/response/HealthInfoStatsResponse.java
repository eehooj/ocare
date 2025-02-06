package com.example.ocare.api.health.dto.response;

import com.example.ocare.api.health.dto.HealthInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HealthInfoStatsResponse {

    List<HealthInfoDto> dailyList;

    List<HealthInfoDto> monthList;
}
