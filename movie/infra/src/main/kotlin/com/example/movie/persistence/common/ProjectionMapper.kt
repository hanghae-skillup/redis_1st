package com.example.movie.persistence.common

import com.example.movie.domain.movie.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.theater.model.Theater
import com.example.movie.persistence.genre.projection.GenreProjection
import com.example.movie.persistence.movie.projection.MovieProjection
import com.example.movie.persistence.screening.projection.ScreeningProjection
import com.example.movie.persistence.theater.projection.TheaterProjection
import java.time.LocalDateTime

object MovieProjectionMapper {
    fun MovieProjection.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            rating = rating,
            releaseDate = releaseDate,
            thumbnailUrl = thumbnailUrl,
            runningTime = runningTime,
            genre = genre.toDomain(),
            screenings = screenings.map { it.toDomain() },
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun GenreProjection.toDomain(): Genre {
        return Genre(
            id = id,
            name = name,
            description = "",
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun ScreeningProjection.toDomain(): Screening {
        return Screening(
            id = id,
            movieId = 0,
            theater = theater.toDomain(),
            screeningTime = screeningTime,
            screeningEndTime = screeningEndTime,
            status = status,
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun TheaterProjection.toDomain(): Theater {
        return Theater(
            id = id,
            name = name,
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }
}