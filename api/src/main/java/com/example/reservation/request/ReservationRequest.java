package com.example.reservation.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ReservationRequest {
    private Long memberId;
    private Long screeningId;
    private List<Long> seatIds;

    public ReservationRequest(Long memberId, Long screeningId, List<Long> seatIds) {
        this.memberId = memberId;
        this.screeningId = screeningId;
        this.seatIds = seatIds;
    }

    public ReservationServiceRequest toServiceRequest() {
        return ReservationServiceRequest.builder()
                .memberId(memberId)
                .screeningId(screeningId)
                .seatIds(seatIds)
                .build();
    }
}
