package com.example.domain.seatReservation.service;

import com.example.domain.seatReservation.entity.SeatReservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatReservationRepository {
    public SeatReservation getSeatReservationInfo(Long seatReservationId);
    public List<SeatReservation> getSeatReservationInfoByUser(Long userId);
    Boolean checkReserveAble(List<String> seats);

    void saveSeats(List<SeatReservation> seatReservations);
}
