package com.example.reservation.response;

import com.example.entity.reservation.Reservation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationServiceResponse {

    private Long reservationId;
    private Long userId;
    private Long screeningId;
    private int seatCount;

    @Builder
    public ReservationServiceResponse(Long reservationId, Long userId, Long screeningId, int seatCount) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.screeningId = screeningId;
        this.seatCount = seatCount;
    }

    public static ReservationServiceResponse of (Reservation reservation) {
        return ReservationServiceResponse.builder()
                .reservationId(reservation.getId())
                .screeningId(reservation.getScreening().getId())
                .userId(reservation.getUser().getId())
                .seatCount(reservation.getReservedSeats().size())
                .build();
    }
}
