package com.study.cinema.infra.jpa.movie

import com.querydsl.core.group.GroupBy
import com.querydsl.core.group.GroupBy.groupBy
import com.querydsl.core.group.GroupBy.list
import com.querydsl.jpa.impl.JPAQueryFactory
import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.MovieInfo
import com.study.cinema.domain.movie.QMovie
import com.study.cinema.domain.movie.QMovieInfo_MovieSchedule
import com.study.cinema.domain.movie.QMovieInfo_TheaterScheduleArea
import com.study.cinema.domain.schedule.QMovieSchedule
import com.study.cinema.domain.theater.QTheater
import org.springframework.cache.annotation.Cacheable

class MovieScheduleRepositoryCustomImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : MovieScheduleRepositoryCustom {

    override fun searchCinemaSchedule(
        cinemaId: Long,
        title: String?,
        genre: Genre?,
    ): List<MovieInfo.MovieSchedule> {
        return jpaQueryFactory.from(qMovieSchedule)
            .innerJoin(qMovieSchedule.movie, qMovie)
            .innerJoin(qMovieSchedule.theater, qTheater)
            .where(
                qMovieSchedule.cinema.id.eq(cinemaId),
                title?.let { qMovie.title.eq(title) },
                genre?.let { qMovie.genre.`in`(it) },
            )
            .orderBy(qMovie.releaseDate.desc())
            .transform(
                groupBy(qMovie.id)
                    .list(
                        QMovieInfo_MovieSchedule(
                            qMovie.id,
                            qMovie.title,
                            qMovie.movieRating,
                            qMovie.releaseDate,
                            qMovie.thumbnailUrl,
                            qMovie.runningTimeMinutes,
                            qMovie.genre,
                            list(
                                QMovieInfo_TheaterScheduleArea(
                                    qMovieSchedule.id,
                                    qTheater.title,
                                    qMovieSchedule.startAt,
                                    qMovieSchedule.endAt,
                                )
                            )
                        )
                    )
            )
    }

    companion object {
        val qMovieSchedule: QMovieSchedule = QMovieSchedule.movieSchedule
        val qMovie: QMovie = QMovie.movie
        val qTheater: QTheater = QTheater.theater
    }
}