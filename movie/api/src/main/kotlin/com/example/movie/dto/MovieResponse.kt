package com.example.movie.dto

import com.example.movie.domain.movie.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.theater.model.Theater
import java.time.LocalDate
import java.time.LocalDateTime

data class MovieResponse(
    val id: Long,
    val title: String,
    val rating: Rating,
    val genre: GenreResponse,
    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,
    val screenings: List<ScreeningResponse>
) {
    companion object {
        fun from(movie: Movie, screenings: List<Screening>): MovieResponse {
            return MovieResponse(
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                genre = GenreResponse.from(movie.genre),
                releaseDate = movie.releaseDate,
                thumbnailUrl = movie.thumbnailUrl,
                runningTime = movie.runningTime,
                screenings = screenings.map { ScreeningResponse.from(it) }
            )
        }
    }
}

data class GenreResponse(
    val id: Long,
    val name: String
) {
    companion object {
        fun from(genre: Genre): GenreResponse {
            return GenreResponse(
                id = genre.id,
                name = genre.name
            )
        }
    }
}

data class ScreeningResponse(
    val id: Long,
    val screeningTime: LocalDateTime,
    val screeningEndTime: LocalDateTime,
    val theater: TheaterResponse
) {
    companion object {
        fun from(screening: Screening): ScreeningResponse {
            return ScreeningResponse(
                id = screening.id,
                screeningTime = screening.screeningTime,
                screeningEndTime = screening.screeningEndTime,
                theater = TheaterResponse.from(screening.theater)
            )
        }
    }
}

data class TheaterResponse(
    val id: Long,
    val name: String
) {
    companion object {
        fun from(theater: Theater): TheaterResponse {
            return TheaterResponse(
                id = theater.id,
                name = theater.name
            )
        }
    }
}