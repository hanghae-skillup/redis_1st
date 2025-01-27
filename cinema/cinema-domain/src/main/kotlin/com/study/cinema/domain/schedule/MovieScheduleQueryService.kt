package com.study.cinema.domain.schedule

import com.study.cinema.domain.movie.MovieInfo
import com.study.cinema.infra.jpa.movie.MovieScheduleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class MovieScheduleQueryService(
    private val movieScheduleRepository: MovieScheduleRepository,
) {
    fun findByCinemaId(cinemaId: Long): List<MovieInfo.MovieSchedule> {
        val schedules = movieScheduleRepository.findByCinemaId(cinemaId)
        return schedules.groupBy { it.movie.id }
            .map { it.value }
            .map {
                MovieInfo.MovieSchedule(it.first().movie, it.sortedBy { it.startAt })
            }
            .sortedByDescending { it.releaseDate }
    }

}
