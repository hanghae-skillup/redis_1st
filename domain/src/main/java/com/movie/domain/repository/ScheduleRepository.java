package com.movie.domain.repository;

import com.movie.domain.entity.Schedule;
import java.util.List;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    List<Schedule> findAllFetchMovieTheater();
}