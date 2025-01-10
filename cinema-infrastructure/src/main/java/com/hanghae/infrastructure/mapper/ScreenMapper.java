package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.Screen;
import com.hanghae.infrastructure.entity.ScreenEntity;

public class ScreenMapper {
    public static Screen toDomain(ScreenEntity entity) {
        return new Screen(
                entity.getId(),
                entity.getScreenName(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedBy(),
                entity.getUpdatedAt()
        );
    }

    public static ScreenEntity toEntity(Screen domain) {
        ScreenEntity entity = ScreenEntity.builder()
                .id(domain.getScreenId())
                .screenName(domain.getScreenName())
                .createdBy(domain.getCreatedBy())
                .createdAt(domain.getCreatedAt())
                .updatedBy(domain.getUpdatedBy())
                .updatedAt(domain.getUpdatedAt())
                .build();
        return entity;
    }
}
