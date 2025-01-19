package com.example.movie.persistence.movie.projection

import com.example.movie.domain.genre.model.Genre
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.model.Rating
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.domain.theater.model.Theater
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.LocalDate
import java.time.LocalDateTime

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class MovieCacheDto(
    val id: Long,
    val title: String,
    val rating: Rating,
    val genre: GenreCacheDto,
    val releaseDate: LocalDate,
    val thumbnailUrl: String,
    val runningTime: Int,
    val screenings: List<ScreeningCacheDto>,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime
) {
    fun toDomain(): Movie = Movie(
        id = id,
        title = title,
        rating = rating,
        genre = genre.toDomain(),
        releaseDate = releaseDate,
        thumbnailUrl = thumbnailUrl,
        runningTime = runningTime,
        screenings = screenings.map { it.toDomain() },
        createdBy = createdBy,
        createdAt = createdAt,
        updatedBy = updatedBy,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(movie: Movie): MovieCacheDto = MovieCacheDto(
            id = movie.id,
            title = movie.title,
            rating = movie.rating,
            genre = GenreCacheDto.fromDomain(movie.genre),
            releaseDate = movie.releaseDate,
            thumbnailUrl = movie.thumbnailUrl,
            runningTime = movie.runningTime,
            screenings = movie.screenings.map { ScreeningCacheDto.fromDomain(it) },
            createdBy = movie.createdBy,
            createdAt = movie.createdAt,
            updatedBy = movie.updatedBy,
            updatedAt = movie.updatedAt
        )
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class GenreCacheDto(
    val id: Long,
    val name: String,
    val description: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime
) {
    fun toDomain(): Genre = Genre(
        id = id,
        name = name,
        description = description,
        createdBy = createdBy,
        createdAt = createdAt,
        updatedBy = updatedBy,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(genre: Genre): GenreCacheDto = GenreCacheDto(
            id = genre.id,
            name = genre.name,
            description = genre.description,
            createdBy = genre.createdBy,
            createdAt = genre.createdAt,
            updatedBy = genre.updatedBy,
            updatedAt = genre.updatedAt
        )
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class ScreeningCacheDto(
    val id: Long,
    val movieId: Long,
    val theater: TheaterCacheDto,
    val screeningTime: LocalDateTime,
    val screeningEndTime: LocalDateTime,
    val status: ScreeningStatus,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime
) {
    fun toDomain(): Screening = Screening(
        id = id,
        movieId = movieId,
        theater = theater.toDomain(),
        screeningTime = screeningTime,
        screeningEndTime = screeningEndTime,
        status = status,
        createdBy = createdBy,
        createdAt = createdAt,
        updatedBy = updatedBy,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(screening: Screening): ScreeningCacheDto = ScreeningCacheDto(
            id = screening.id,
            movieId = screening.movieId,
            theater = TheaterCacheDto.fromDomain(screening.theater),
            screeningTime = screening.screeningTime,
            screeningEndTime = screening.screeningEndTime,
            status = screening.status,
            createdBy = screening.createdBy,
            createdAt = screening.createdAt,
            updatedBy = screening.updatedBy,
            updatedAt = screening.updatedAt
        )
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
data class TheaterCacheDto(
    val id: Long,
    val name: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime
) {
    fun toDomain(): Theater = Theater(
        id = id,
        name = name,
        createdBy = createdBy,
        createdAt = createdAt,
        updatedBy = updatedBy,
        updatedAt = updatedAt
    )

    companion object {
        fun fromDomain(theater: Theater): TheaterCacheDto = TheaterCacheDto(
            id = theater.id,
            name = theater.name,
            createdBy = theater.createdBy,
            createdAt = theater.createdAt,
            updatedBy = theater.updatedBy,
            updatedAt = theater.updatedAt
        )
    }
}

fun List<Movie>.toMovieCacheDtos(): List<MovieCacheDto> = map { MovieCacheDto.fromDomain(it) }
fun List<MovieCacheDto>.toMovies(): List<Movie> = map { it.toDomain() }

fun List<Screening>.toScreeningCacheDtos(): List<ScreeningCacheDto> = map { ScreeningCacheDto.fromDomain(it) }
fun List<ScreeningCacheDto>.toScreenings(): List<Screening> = map { it.toDomain() }