package com.movie.infra.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.repository.ScheduleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long>, ScheduleRepository {
}