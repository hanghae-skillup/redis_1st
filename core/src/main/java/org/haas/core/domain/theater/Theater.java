package org.haas.core.domain.theater;

import lombok.*;
import org.haas.core.domain.reservation.ReservationStatus;
import org.haas.core.domain.seat.Seat;

import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Theater {

    private Long theaterId;

    private String name;

    private List<Seat> seats = new ArrayList<>();

    public Theater() {
        initializeSeats();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    private void initializeSeats() {
        for (int row = 1; row <= 5; row++) {
            for (int column = 1; column <=5; column++) {
                seats.add( new Seat( row, column, ReservationStatus.NOT_RESERVED));
            }
        }
    }

    public Seat getSeat(int row, int column) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst()
                .orElse(null)
                ;
    }

    public boolean reserveSeat(int row, int column) {
        Seat seat = getSeat(row, column);
        if (seat != null && seat.reservationAvailable()) {
            seat.reserveSeat();
            return true;
        }

        return false;
    }

    public boolean cancelReservation(int row, int column) {
        Seat seat = getSeat(row, column);
        if (seat != null && !seat.reservationAvailable()) {
            seat.cancelReservation();
            return true;
        }
        return false;
    }

}
