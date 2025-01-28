package org.haas.core.domain.reservation;

import lombok.Getter;
import org.haas.core.domain.seat.Seat;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class Reservation {

    private Long reservationId;

    private Set<Seat> seats;

    private LocalDateTime reservationTime;

    private ReservationStatus reservationStatus;

}
