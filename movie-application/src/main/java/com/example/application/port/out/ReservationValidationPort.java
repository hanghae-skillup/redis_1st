package com.example.application.port.out;

import com.example.domain.model.entity.ScreeningSeat;
import com.example.domain.model.entity.Seat;
import java.util.List;

public interface ReservationValidationPort {
    void validateMaxSeatsPerScreening(int existingReservations, int newSeatCount);
    void validateSeatsAreConsecutive(List<Seat> seats);
    void validateSeatsExist(List<Long> requestedSeatIds, List<ScreeningSeat> foundSeats);
}
