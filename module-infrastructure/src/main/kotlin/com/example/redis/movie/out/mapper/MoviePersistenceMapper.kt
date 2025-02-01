package com.example.redis.movie.out.mapper

import com.example.redis.movie.Movie
import com.example.redis.movie.out.persistence.jpa.MovieEntity
import com.example.redis.movie.Screening
import com.example.redis.movie.out.persistence.jpa.ScreeningEntity
import com.example.redis.theater.out.mapper.TheaterPersistenceMapper.Companion.toTheaterDomain

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
                movieGenre = entity.movieGenre.stream().map { it.name }.toList().toMutableList(),
                screenings = entity.screening.stream().map { toScreeningDomain(it) }.toList().toMutableList(),
                createAt = entity.createAt,
                updateAt = entity.updateAt
            )
        }
        fun toScreeningDomain(entity: ScreeningEntity): Screening {
            requireNotNull(entity.id)
            return Screening(
                screeningId = entity.id,
                startTime = entity.startTime,
                endTime = entity.endTime,
                theater = toTheaterDomain(entity.theater)
            )
        }

    }
}