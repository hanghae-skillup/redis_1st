package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;

public class ScreeningScheduleMapper {
    public static ScreeningSchedule toDomain(ScreeningScheduleEntity entity) {
        return new ScreeningSchedule(
                entity.getId(),
                MovieMapper.toDomain(entity.getMovieEntity()),
                ScreenMapper.toDomain(entity.getScreenEntity()),
                entity.getShowStartDatetime(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedBy(),
                entity.getUpdatedAt()
        );
    }

    public static ScreeningScheduleEntity toEntity(ScreeningSchedule domain) {
        ScreeningScheduleEntity entity = ScreeningScheduleEntity.builder()
                .id(domain.getScheduleId())
                .movieEntity(MovieMapper.toEntity(domain.getMovie()))
                .screenEntity(ScreenMapper.toEntity(domain.getScreen()))
                .showStartDatetime(domain.getShowStartDatetime())
                .createdBy(domain.getCreatedBy())
                .createdAt(domain.getCreatedAt())
                .updatedBy(domain.getUpdatedBy())
                .updatedAt(domain.getUpdatedAt())
                .build();
        return entity;
    }
}
