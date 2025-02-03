package com.hanghae.infrastructure.adapter.repository;

import com.hanghae.application.port.out.repository.ScreenSeatLayoutRepositoryPort;
import com.hanghae.domain.model.ScreenSeatLayout;
import com.hanghae.infrastructure.entity.ScreenSeatLayoutEntity;
import com.hanghae.infrastructure.mapper.ScreenSeatLayoutMapper;
import com.hanghae.infrastructure.repository.ScreenSeatLayoutRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScreenSeatLayoutAdapter implements ScreenSeatLayoutRepositoryPort {
    private final ScreenSeatLayoutRepositoryJpa repository;

    @Override
    public ScreenSeatLayout findBySeatRowAndScreenId(String seatRow, Long screenId) {
        // 상영관id로 상영관 좌석정보 조회 
        ScreenSeatLayoutEntity screenSeatLayout = repository.findOneBySeatRowAndScreenId(seatRow, screenId);

        //엔티티를 도메인으로 변환하여 리턴
        return ScreenSeatLayoutMapper.toDomain(screenSeatLayout);
    }
}
