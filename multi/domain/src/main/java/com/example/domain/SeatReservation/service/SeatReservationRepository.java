package com.example.domain.SeatReservation.service;

import com.example.domain.SeatReservation.SeatReservation;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatReservationRepository {
    public SeatReservation getSeatReservationInfo(Long seatReservationId);

}
