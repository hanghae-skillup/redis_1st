package com.movie.domain.repository;

import com.movie.domain.entity.Schedule;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Schedule save(Schedule schedule);
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
}