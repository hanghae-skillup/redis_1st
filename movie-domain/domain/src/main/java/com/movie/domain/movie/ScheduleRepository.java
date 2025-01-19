package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;

import java.util.List;

public interface ScheduleRepository {

    List<ScheduleInfo.Get> getSchedules(Long theaterId);

    List<ScheduleInfo.Get> getSchedules(ScheduleCommand.Search search);

}
