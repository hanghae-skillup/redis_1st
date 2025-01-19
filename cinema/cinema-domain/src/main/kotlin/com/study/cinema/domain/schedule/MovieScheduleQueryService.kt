package com.study.cinema.domain.schedule

import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.MovieInfo
import com.study.cinema.infra.jpa.movie.MovieScheduleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class MovieScheduleQueryService(
    private val movieScheduleRepository: MovieScheduleRepository,
) {
    fun searchCinemaSchedule(
        cinemaId: Long,
        title: String?,
        genre: Genre?,
    ): List<MovieInfo.MovieSchedule> {
        val schedules = movieScheduleRepository.searchCinemaSchedule(
            cinemaId = cinemaId,
            genre = genre,
            title = title,
            )
        return schedules
            .sortedByDescending { it.releaseDate }
    }

}
