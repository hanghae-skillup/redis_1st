package com.study.cinema.infra.jpa.movie

import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.jpa.impl.JPAQueryFactory
import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.MovieInfo
import com.study.cinema.domain.movie.QMovie
import com.study.cinema.domain.movie.QMovieInfo_MovieSchedule
import com.study.cinema.domain.schedule.QMovieSchedule
import com.study.cinema.domain.theater.QTheater

class MovieScheduleRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
): MovieScheduleRepositoryCustom {

    override fun searchCinemaSchedule(
        cinemaId: Long,
        title: String?,
        genre: Genre?,
    ): List<MovieInfo.MovieSchedule> {
        return jpaQueryFactory.from(qMovieSchedule)
            .join(qMovieSchedule.movie, qMovie).fetchJoin()
            .join(qMovieSchedule.theater, qTheater).fetchJoin()
            .where(
                qMovieSchedule.cinema.id.eq(cinemaId),
                title?.let { qMovie.title.like(title) },
                genre?.let { qMovie.genre.`in`(it) },
            )
            .transform(
                groupBy(qMovie.id).list(
                    QMovieInfo_MovieSchedule(
                        qMovie,
                        list(qMovieSchedule),
                    ),
                ),
            )
    }

    companion object {
        val qMovieSchedule: QMovieSchedule = QMovieSchedule.movieSchedule
        val qMovie: QMovie = QMovie.movie
        val qTheater: QTheater = QTheater.theater
    }
}