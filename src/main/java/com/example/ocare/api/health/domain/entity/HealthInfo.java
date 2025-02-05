package com.example.ocare.api.health.domain.entity;


import com.example.ocare.global.common.entity.EntityExtension;
import com.example.ocare.global.util.DateUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "HEATH_INFO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthInfo extends EntityExtension {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long healthInfoId;

    @Column(nullable = false)
    private String recordKey;

    @Column(nullable = false)
    private LocalDateTime startDt;

    @Column(nullable = false)
    private LocalDateTime endDt;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private double distance;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private double calories;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private double steps;

    private HealthInfo(LocalDateTime startDt, LocalDateTime endDt,
                      double distance, double calories, double steps, String recordKey) {
        this.startDt = startDt;
        this.endDt = endDt;
        this.distance = distance;
        this.calories = calories;
        this.steps = steps;
        this.createDt = LocalDateTime.now();
        this.recordKey = recordKey;
    }

    public static HealthInfo redisToEntity(HealthInfoRedis healthInfoRedis) {
        return new HealthInfo(
                DateUtil.parseDate(healthInfoRedis.getPeriod().getFrom()),
                DateUtil.parseDate(healthInfoRedis.getPeriod().getTo()),
                healthInfoRedis.getDistance().getValue(),
                healthInfoRedis.getCalories().getValue(),
                healthInfoRedis.getSteps(),
                healthInfoRedis.getRecordKey());
    }
}
