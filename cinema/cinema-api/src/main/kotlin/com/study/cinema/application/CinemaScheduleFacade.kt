package com.study.cinema.application

import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.schedule.MovieScheduleQueryService
import org.springframework.stereotype.Component

@Component
class CinemaScheduleFacade(
    private val movieScheduleQueryService: MovieScheduleQueryService
) {
    fun getSchedulesByCinemaId(
        cinemaId: Long,
        title: String?,
        genre: Genre?,
    ) = movieScheduleQueryService.searchCinemaSchedule(
        cinemaId = cinemaId,
        title = title,
        genre = genre,
    )
}