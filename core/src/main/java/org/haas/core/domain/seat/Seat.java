package org.haas.core.domain.seat;

import lombok.Getter;
import org.haas.core.domain.reservation.ReservationStatus;

@Getter
public class Seat {

    private Long seatId;

    private int row;

    private int column;

    private ReservationStatus reservationStatus;

    public Seat(int row, int column, ReservationStatus reservationStatus) {
        this.row = row;
        this.column = column;
        this.reservationStatus = reservationStatus;
    }

    public void reserveSeat() {
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public void cancelReservation() {
        this.reservationStatus = ReservationStatus.NOT_RESERVED;
    }

    public boolean reservationAvailable() {
        return this.reservationStatus == ReservationStatus.NOT_RESERVED;
    }


}
