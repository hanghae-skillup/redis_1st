package hanghae.infrastructure.adapter;

import hanghae.domain.entity.Seat;
import hanghae.domain.port.SeatRepository;
import hanghae.infrastructure.repository.JpaSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaSeatRepositoryAdapter implements SeatRepository {

    private final JpaSeatRepository jpaSeatRepository;

    @Override
    public Optional<Seat> findSeatBySeatNameAndScreenId(String seatName, Long screenId) {
        return jpaSeatRepository.findSeatBySeatNameAndScreen_ScreenId(seatName, screenId);
    }
}
