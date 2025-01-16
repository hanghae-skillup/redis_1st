package com.movie.moviedomain.movie;

import com.movie.moviedomain.movie.domain.Schedule;
import com.movie.moviedomain.movie.dto.info.ScheduleInfo;

import java.util.List;

public interface ScheduleRepository {

    List<Schedule> getSchedules(Long theaterId);

    List<ScheduleInfo.Get> getSchedules(ScheduleStatement.Search search);

}
