package hanghae.infrastructure.repository;

import hanghae.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findSeatBySeatNameAndScreen_ScreenId(String seatName, Long screenId);
}
