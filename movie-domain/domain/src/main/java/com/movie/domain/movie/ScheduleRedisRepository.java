package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;

import java.util.List;

public interface ScheduleRedisRepository {

    void save(ScheduleCommand.Search search, List<ScheduleInfo.Get> schedules);

    List<ScheduleInfo.Get> find(ScheduleCommand.Search search);

}
