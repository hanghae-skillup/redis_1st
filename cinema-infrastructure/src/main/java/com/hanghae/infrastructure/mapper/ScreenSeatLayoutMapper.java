package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.ScreenSeatLayout;
import com.hanghae.infrastructure.entity.ScreenSeatLayoutEntity;

public class ScreenSeatLayoutMapper {
    public static ScreenSeatLayout toDomain(ScreenSeatLayoutEntity screenSeatLayoutEntity) {
        return new ScreenSeatLayout(
                screenSeatLayoutEntity.getId(),
                ScreenMapper.toDomain(screenSeatLayoutEntity.getScreenEntity()),
                screenSeatLayoutEntity.getSeatRow(),
                screenSeatLayoutEntity.getMaxSeatNumber(),
                screenSeatLayoutEntity.getCreatedBy(),
                screenSeatLayoutEntity.getCreatedAt(),
                screenSeatLayoutEntity.getUpdatedBy(),
                screenSeatLayoutEntity.getCreatedAt()
        );
    }

    public static ScreenSeatLayoutEntity toEntity(ScreenSeatLayout screenSeatLayout) {
        return ScreenSeatLayoutEntity.builder()
                .id(screenSeatLayout.getSeatLayoutId())
                .screenEntity(ScreenMapper.toEntity(screenSeatLayout.getScreen()))
                .seatRow(screenSeatLayout.getSeatRow())
                .maxSeatNumber(screenSeatLayout.getMaxSeatNumber())
                .createdBy(screenSeatLayout.getCreatedBy())
                .updatedBy(screenSeatLayout.getUpdatedBy())
                .build();
    }
}
