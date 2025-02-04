package hanghae.application.service;

import hanghae.application.port.ScheduleService;
import hanghae.domain.entity.Schedule;
import hanghae.domain.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findScheduleById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("No schedule found with id: " + scheduleId));
    }
}
