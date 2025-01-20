package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;

public class ScreeningScheduleMapper {
    public static ScreeningSchedule toDomain(ScreeningScheduleEntity entity) {
        return new ScreeningSchedule(
                entity.getId(),
                MovieMapper.toDomain(entity.getMovieEntity()),
                ScreenMapper.toDomain(entity.getScreenEntity()),
                entity.getShowStartAt(),
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
                .showStartAt(domain.getShowStartAt())
                .createdBy(domain.getCreatedBy())
                .updatedBy(domain.getUpdatedBy())
                .build();
        return entity;
    }
}
