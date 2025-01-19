package com.example.movie.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
