package com.example.application.adapter;

import com.example.application.port.out.ReservationValidationPort;
import com.example.domain.model.entity.ScreeningSeat;
import com.example.domain.model.entity.Seat;
import com.example.domain.validation.ReservationValidation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReservationValidationAdapter implements ReservationValidationPort {

    private final ReservationValidation reservationValidation;

    @Override
    public void validateMaxSeatsPerScreening(int existingReservations, int newSeatCount) {
        reservationValidation.validateMaxSeatsPerScreening(existingReservations, newSeatCount);
    }

    @Override
    public void validateSeatsAreConsecutive(List<Seat> seats) {
        reservationValidation.validateSeatsAreConsecutive(seats);
    }

    @Override
    public void validateSeatsExist(List<Long> requestedSeatIds, List<ScreeningSeat> foundSeats) {
        reservationValidation.validateSeatsExist(requestedSeatIds, foundSeats);
    }

}
