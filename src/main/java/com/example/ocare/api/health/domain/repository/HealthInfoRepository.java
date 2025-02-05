package com.example.ocare.api.health.domain.repository;

import com.example.ocare.api.health.domain.entity.HealthInfo;
import com.example.ocare.api.health.domain.repository.custom.HealthInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthInfoRepository extends JpaRepository<HealthInfo, Long>, HealthInfoRepositoryCustom {
}
