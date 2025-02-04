package hanghae.domain.port;

import hanghae.domain.entity.ScheduleSeat;

import java.util.Optional;

public interface ScheduleSeatRepository {

    Optional<ScheduleSeat> findScheduleSeatByIds(Long scheduleId, Long seatId);
}
