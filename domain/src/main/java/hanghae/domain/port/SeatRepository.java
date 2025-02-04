package hanghae.domain.port;

import hanghae.domain.entity.Seat;

import java.util.Optional;

public interface SeatRepository {
    Optional<Seat> findSeatBySeatNameAndScreenId(String seatName, Long screenId);
}
