package hanghae.infrastructure.adapter;

import hanghae.domain.entity.Schedule;
import hanghae.domain.port.ScheduleRepository;
import hanghae.infrastructure.repository.JpaScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaScheduleRepositoryAdapter implements ScheduleRepository {

    private final JpaScheduleRepository jpaScheduleRepository;

    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        return jpaScheduleRepository.findById(scheduleId);
    }
}
