package hanghae.application.dto.response;

import hanghae.domain.entity.Reservation;
import hanghae.domain.entity.ReservationSeat;
import hanghae.domain.entity.Seat;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationResponse(
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<String> seatName
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getStartDateTime(),
                reservation.getEndDateTime(),
                getSeatNames(reservation)
        );
    }

    private static List<String> getSeatNames(Reservation reservation) {
        return reservation.getReservationSeats().stream()
                .map(ReservationSeat::getSeat)
                .map(Seat::getSeatName)
                .toList();
    }
}
