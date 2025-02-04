package com.example.ocare.api.health.domain.entity;


import com.example.ocare.api.user.domain.entity.User;
import com.example.ocare.global.common.entity.EntityExtension;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "HEATH_INFO")
@Getter
public class HealthInfo extends EntityExtension {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long healthInfoId;

    @Column(nullable = false)
    private LocalDateTime start_dt;

    @Column(nullable = false)
    private LocalDateTime end_dt;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private double distance;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private double calories;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int steps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
