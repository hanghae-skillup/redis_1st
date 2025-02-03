package project.redis.domain.seat;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import project.redis.domain.cinema.Cinema;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Seat {
    UUID seatId;
    String seatNumber;
    Cinema cinema;

    public static Seat generateSeat(UUID seatId, String seatNumber, Cinema cinema) {
        return new Seat(seatId, seatNumber, cinema);
    }
}
