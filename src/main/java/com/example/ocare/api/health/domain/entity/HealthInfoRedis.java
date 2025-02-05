package com.example.ocare.api.health.domain.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("healthInfoRedis")
public class HealthInfoRedis {

    @Id
    private String id;

    private String recordKey;

    private Period period;

    private Distance distance;

    private Calories calories;

    private double steps;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Period {
        private String from;

        private String to;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Distance {
        private String unit;
        private double value;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Calories {
        private String unit;
        private double value;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public void setRecordKey(String recordKey) { this.recordKey = recordKey; }

}

