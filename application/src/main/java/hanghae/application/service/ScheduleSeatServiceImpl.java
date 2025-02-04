package hanghae.application.service;

import hanghae.application.port.ScheduleSeatService;
import hanghae.domain.entity.ScheduleSeat;
import hanghae.domain.port.ScheduleSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleSeatServiceImpl implements ScheduleSeatService {

    private final ScheduleSeatRepository scheduleSeatRepository;

    @Override
    @Transactional
    public ScheduleSeat findScheduleSeatByIds(Long scheduleId, Long seatId) {
        return scheduleSeatRepository.findScheduleSeatByIds(scheduleId, seatId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No ScheduleSeat found with ids: " + scheduleId + ", " + seatId
                        )
                );
    }
}
