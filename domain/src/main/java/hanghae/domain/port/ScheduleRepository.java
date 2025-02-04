package hanghae.domain.port;

import hanghae.domain.entity.Schedule;

import java.util.Optional;

public interface ScheduleRepository {
    Optional<Schedule> findScheduleById(Long scheduleId);
}
