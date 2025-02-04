package hanghae.infrastructure.adapter;

import hanghae.domain.entity.ScheduleSeat;
import hanghae.domain.port.ScheduleSeatRepository;
import hanghae.infrastructure.repository.JpaScheduleSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaScheduleSeatRepositoryAdapter implements ScheduleSeatRepository {

    private final JpaScheduleSeatRepository jpaScheduleSeatRepository;

    @Override
    public Optional<ScheduleSeat> findScheduleSeatByIds(Long scheduleId, Long seatId) {
        return jpaScheduleSeatRepository.findScheduleSeatByScheduleIdAndSeatId(scheduleId, seatId);
    }
}
