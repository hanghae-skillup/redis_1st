package com.example.reservation.request;

import lombok.Getter;

import java.util.List;

import static com.example.exception.BusinessError.*;


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

    private void validate() {
        if (memberId == null) {
            throw USER_LOGIN_ERROR.exception();
        }
        if (screeningId == null) {
            throw RESERVATION_SCREENING_SELECT_ERROR.exception();
        }
        if (seatIds.isEmpty()) {
            throw RESERVATION_SEAT_SELECT_ERROR.exception();
        }
    }

    public ReservationServiceRequest toServiceRequest() {
        this.validate();
        return ReservationServiceRequest.builder()
                .memberId(memberId)
                .screeningId(screeningId)
                .seatIds(seatIds)
                .build();
    }
}
