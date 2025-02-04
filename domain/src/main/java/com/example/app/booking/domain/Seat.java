package com.example.app.booking.domain;

import com.example.app.common.exception.APIException;
import com.example.app.movie.type.TheaterSeat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ALREADY_OCCUPIED;

@Builder
public record Seat(
        Long id,
        Long bookingId,
        Long movieId,
        Long showtimeId,
        Long theaterId,
        LocalDate bookingDate,
        TheaterSeat theaterSeat,
        boolean reserved
) {

    public static void checkSeatsAvailable(List<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.reserved()) {
                throw new APIException(SEAT_ALREADY_OCCUPIED);
            }
        }
    }
}
