package project.redis.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import project.redis.domain.screening.Screening;
import project.redis.domain.seat.Seat;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class Reservation {
    UUID reservationId;
    LocalDateTime reservationTime;
    String username;
    Screening screening;
    List<Seat> seats;


    public static Reservation generateReservation(
            UUID reservationId, LocalDateTime reservationTime,
            String username,
            Screening screening,
            List<Seat> seats) {
        return new Reservation(reservationId, reservationTime, username, screening, seats);
    }

    public boolean isSeatAvailable(Seat seat) {
        return !seats.contains(seat);
    }

    public void addSeats(List<Seat> newSeats) {
        if (seats.size() + newSeats.size() > 5) {
            throw new IllegalArgumentException("5개 이상 예약할 수 없습니다");
        }
        seats.addAll(newSeats);
    }

}
