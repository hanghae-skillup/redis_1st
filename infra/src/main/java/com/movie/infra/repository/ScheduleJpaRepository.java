package com.movie.infra.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.ScheduleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long>, ScheduleRepository {
    @Override
    default List<Schedule> findAll() {
        return findByStartAtGreaterThan(LocalDateTime.now());
    }

    List<Schedule> findByStartAtGreaterThan(LocalDateTime currentTime);
}