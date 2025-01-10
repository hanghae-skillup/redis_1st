package com.movie.moviedomain.movie;

import com.movie.moviedomain.movie.domain.Schedule;

import java.util.List;

public interface ScheduleRepository {

    List<Schedule> getSchedules(Long theaterId);

}
