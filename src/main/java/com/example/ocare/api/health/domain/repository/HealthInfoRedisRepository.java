package com.example.ocare.api.health.domain.repository;

import com.example.ocare.api.health.domain.entity.HealthInfoRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HealthInfoRedisRepository extends CrudRepository<HealthInfoRedis, String> {

    List<HealthInfoRedis> findAll();
}
