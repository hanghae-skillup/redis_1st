package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.schedule.MovieSchedule

interface MovieScheduleRepositoryCustom {
    fun findByCinemaId(cinemaId: Long): List<MovieSchedule>
}