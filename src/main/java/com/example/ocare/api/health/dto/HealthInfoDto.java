package com.example.ocare.api.health.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class HealthInfoDto {

    private String date;

    private double steps;

    private double calories;

    private double distance;

    private String recordKey;

    @QueryProjection
    public HealthInfoDto(String date, double steps, double calories, double distance, String recordKey) {
        this.date = date;
        this.steps = steps;
        this.calories = calories;
        this.distance = distance;
        this.recordKey = recordKey;
    }
}
