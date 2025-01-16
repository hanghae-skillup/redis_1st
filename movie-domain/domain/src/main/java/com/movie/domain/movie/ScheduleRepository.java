package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import com.movie.domain.movie.domain.Schedule;

import java.util.List;

public interface ScheduleRepository {

    List<Schedule> getSchedules(Long theaterId);

    List<ScheduleInfo.Get> getSchedules(ScheduleCommand.Search search);

}
