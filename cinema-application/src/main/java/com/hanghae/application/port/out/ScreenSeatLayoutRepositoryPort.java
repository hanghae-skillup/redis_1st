package com.hanghae.application.port.out;

import com.hanghae.domain.model.ScreenSeatLayout;

import java.util.List;

public interface ScreenSeatLayoutRepositoryPort {
    ScreenSeatLayout findBySeatRowAndScreenId(String seatRow, Long screenId);
}
