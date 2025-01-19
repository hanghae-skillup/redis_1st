package com.example.movie.persistence.movie.projection

import com.example.movie.domain.genre.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.theater.model.Theater
import java.time.LocalDateTime

object MovieDtoMapper {
    fun toMovie(
        movie: MovieWithGenreDto,
        screenings: List<ScreeningWithTheaterDto>
    ): Movie {
        return Movie(
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            releaseDate = movie.releaseDate,
            thumbnailUrl = movie.thumbnailUrl,
            runningTime = movie.runningTime,
            genre = toGenre(movie),
            screenings = screenings.map { toScreening(it) },
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun toGenre(movie: MovieWithGenreDto): Genre {
        return Genre(
            id = movie.genreId,
            name = movie.genreName,
            description = "",
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun toScreening(screening: ScreeningWithTheaterDto): Screening {
        return Screening(
            id = screening.id,
            movieId = screening.movieId,
            theater = toTheater(screening),
            screeningTime = screening.screeningTime,
            screeningEndTime = screening.screeningEndTime,
            status = screening.status,
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }

    private fun toTheater(screening: ScreeningWithTheaterDto): Theater {
        return Theater(
            id = screening.theaterId,
            name = screening.theaterName,
            createdBy = "system",
            createdAt = LocalDateTime.now(),
            updatedBy = "system",
            updatedAt = LocalDateTime.now()
        )
    }
}