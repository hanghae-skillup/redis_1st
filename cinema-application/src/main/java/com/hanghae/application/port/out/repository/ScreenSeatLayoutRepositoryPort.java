package com.hanghae.application.port.out.repository;

import com.hanghae.domain.model.ScreenSeatLayout;

public interface ScreenSeatLayoutRepositoryPort {
    ScreenSeatLayout findBySeatRowAndScreenId(String seatRow, Long screenId);
}
