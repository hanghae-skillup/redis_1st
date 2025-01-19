package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleInfo.Get> getSchedules(Long theaterId) {
        return scheduleRepository.getSchedules(theaterId);
    }

    public List<ScheduleInfo.Get> getSchedules(ScheduleCommand.Search search) {
        return scheduleRepository.getSchedules(search);
    }

}
