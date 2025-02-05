package com.example.ocare.api.health.domain.repository.custom;

import com.example.ocare.api.health.dto.HealthInfoDto;
import com.example.ocare.api.health.dto.QHealthInfoDto;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.ocare.api.health.domain.entity.QHealthInfo.healthInfo;

@RequiredArgsConstructor
public class HealthInfoRepositoryCustomImpl implements HealthInfoRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public List<HealthInfoDto> getHealthInfoStats(String format) {
        StringTemplate stringDate = Expressions
                .stringTemplate("DATE_FORMAT({0}, {1})", healthInfo.startDt, ConstantImpl.create(format));

        return factory
                .select(
                        new QHealthInfoDto(
                                stringDate,
                                healthInfo.steps.sum(),
                                healthInfo.calories.sum(),
                                healthInfo.distance.sum(),
                                healthInfo.recordKey
                        )
                )
                .from(healthInfo)
                .groupBy(stringDate, healthInfo.recordKey)
                .orderBy(stringDate.asc(), healthInfo.recordKey.asc())
                .fetch();
    }

}
