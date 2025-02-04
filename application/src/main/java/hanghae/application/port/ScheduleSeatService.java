package hanghae.application.port;

import hanghae.domain.entity.ScheduleSeat;

public interface ScheduleSeatService {

    ScheduleSeat findScheduleSeatByIds(Long scheduleId, Long seatId);
}
