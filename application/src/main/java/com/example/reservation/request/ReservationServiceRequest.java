package com.example.reservation.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationServiceRequest {

    private final Long memberId;
    private final Long screeningId;
    private final List<Long> seatIds;

    @Builder
    public ReservationServiceRequest(Long memberId, Long screeningId, List<Long> seatIds) {
        this.memberId = memberId;
        this.screeningId = screeningId;
        this.seatIds = seatIds;
    }

    public boolean isMemberIdNull() {
        return memberId == null;
    }

    public boolean isScreeningIdNull() {
        return screeningId == null;
    }
}
