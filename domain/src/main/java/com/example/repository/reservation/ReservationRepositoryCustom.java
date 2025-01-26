package com.example.repository.reservation;

import com.example.entity.reservation.Reservation;

import java.util.List;

public interface ReservationRepositoryCustom {
    List<Reservation> findByScreeningIdWithReservedSeats(Long screeningId);
}
