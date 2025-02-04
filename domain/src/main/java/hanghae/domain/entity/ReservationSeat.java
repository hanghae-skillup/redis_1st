package hanghae.domain.entity;

import hanghae.domain.type.ReservationSeatId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "reservation_seat")
@NoArgsConstructor
public class ReservationSeat {

    @EmbeddedId
    private ReservationSeatId reservationSeatId = new ReservationSeatId();

    @ManyToOne(fetch = LAZY)
    @MapsId("reservationId")
    private Reservation reservation;

    @Getter
    @ManyToOne(fetch = LAZY)
    @MapsId("seatId")
    private Seat seat;

    public static ReservationSeat of(Reservation reservation, Seat seat) {
        ReservationSeat reservationSeat = new ReservationSeat();

        reservationSeat.reservation = reservation;
        reservationSeat.seat = seat;

        return reservationSeat;
    }
}
