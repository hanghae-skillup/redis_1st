package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.MovieInfo

interface MovieScheduleRepositoryCustom {
    fun searchCinemaSchedule(
        cinemaId: Long,
        title: String?,
        genre: Genre?,
    ): List<MovieInfo.MovieSchedule>
}