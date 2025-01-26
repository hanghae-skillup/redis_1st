package kr.spartacodingclub.cinema.core.repository

import kr.spartacodingclub.cinema.core.domain.Movie
import kr.spartacodingclub.cinema.core.domain.Theater
import java.time.LocalDateTime

interface MovieRepository {
    fun findAllOrderByReleaseDateDesc(): List<Movie>
    fun findById(id: Long): Movie?
    fun findAllWithScreeningsOrderByReleaseDateDesc(): List<MovieWithScreenings>
}

data class MovieWithScreenings(
    val movie: Movie,
    val screenings: List<MovieScreening>
)

data class MovieScreening(
    val theater: Theater,
    val startTime: LocalDateTime
)
