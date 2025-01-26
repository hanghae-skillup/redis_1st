package com.example.reservation.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationServiceRequest {

    private final Long userId;
    private final Long screeningId;
    private final List<Long> seatIds;

    @Builder
    public ReservationServiceRequest(Long userId, Long screeningId, List<Long> seatIds) {
        this.userId = userId;
        this.screeningId = screeningId;
        this.seatIds = seatIds;
    }
}
