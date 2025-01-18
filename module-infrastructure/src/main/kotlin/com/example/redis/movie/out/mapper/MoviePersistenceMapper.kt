package com.example.redis.movie.out.mapper

import com.example.redis.movie.Movie
import com.example.redis.movie.out.persistence.jpa.MovieEntity
import com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity
import com.example.redis.movie.MovieTheater
import com.example.redis.movie.ScreeningSchedule
import com.example.redis.movie.out.persistence.jpa.ScreeningScheduleEntity

class MoviePersistenceMapper {

    companion object {
        fun toMovieDomain(entity: MovieEntity): Movie {
            requireNotNull(entity.id)
            return Movie(
                movieId = entity.id,
                title = entity.title,
                runningTime = entity.runningTime,
                releaseDate = entity.releaseDate,
                thumbnailImagePath = entity.thumbnailImagePath,
                filmRatings = entity.filmRatings.name,
                movieGenre = entity.movieGenre,
                    //.stream().map { it.name }.toList(),
                theaters = entity.movieTheaters.stream().map { toTheaterDomain(it) }.toList(),
                createAt = entity.createAt,
                updateAt = entity.updateAt
            )
        }

        private fun toTheaterDomain(entity: MovieTheaterEntity): MovieTheater {
            requireNotNull(entity.theater.id)
            return MovieTheater(
                theaterId = entity.theater.id,
                name =  entity.theater.name,
                screeningSchedules = entity.screeningSchedules.stream().map{
                    toScreeningScheduleDomain(it)
                }.toList()
            )
        }

        private fun toScreeningScheduleDomain(entity: ScreeningScheduleEntity): ScreeningSchedule {
            requireNotNull(entity.id)
            return ScreeningSchedule(
                screeningScheduleId = entity.id,
                startTime =  entity.startTime,
                endTime = entity.endTime,
            )
        }
    }
}