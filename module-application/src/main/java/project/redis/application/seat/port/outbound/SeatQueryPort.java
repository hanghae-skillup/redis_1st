package project.redis.application.seat.port.outbound;

import java.util.List;
import java.util.UUID;
import project.redis.domain.seat.Seat;

public interface SeatQueryPort {

    List<Seat> getSeats(List<UUID> seatIds);
}
