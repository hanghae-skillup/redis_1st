package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.schedule.MovieSchedule
import org.springframework.data.jpa.repository.JpaRepository

interface MovieScheduleRepository : JpaRepository<MovieSchedule, Long>, MovieScheduleRepositoryCustom