package com.study.cinema.infra.jpa.movie

import com.study.cinema.domain.movie.QMovie
import com.study.cinema.domain.schedule.MovieSchedule
import com.study.cinema.domain.schedule.QMovieSchedule
import com.study.cinema.domain.theater.QTheater
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class MovieScheduleRepositoryCustomImpl : QuerydslRepositorySupport(MovieSchedule::class.java),
    MovieScheduleRepositoryCustom {

    override fun findByCinemaId(cinemaId: Long): List<MovieSchedule> {
        return from(qMovieSchedule)
            .join(qMovieSchedule.movie, qMovie).fetchJoin()
            .join(qMovieSchedule.theater, qTheater).fetchJoin()
            .where(
                qMovieSchedule.cinema.id.eq(cinemaId)
            )
            .fetch()
    }

    companion object {
        val qMovieSchedule: QMovieSchedule = QMovieSchedule.movieSchedule
        val qMovie: QMovie = QMovie.movie
        val qTheater: QTheater = QTheater.theater
    }
}