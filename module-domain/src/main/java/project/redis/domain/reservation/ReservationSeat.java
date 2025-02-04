package project.redis.domain.reservation;


import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class ReservationSeat {
    UUID reservationSeatId;
    UUID reservationId;
    UUID seatId;

    public static ReservationSeat generateReservationSeat(
            UUID reservationSeatId, UUID reservationId, UUID seatId) {
        return new ReservationSeat(reservationSeatId, reservationId, seatId);
    }
}
