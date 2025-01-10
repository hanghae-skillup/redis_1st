package com.example.movie.persistence.movie.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.screening.repository.ScreeningJpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class MovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository,
    private val screeningJpaRepository: ScreeningJpaRepository
) : MovieRepository {
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllByOrderByReleaseDateDesc().map { it.toDomain() }
    }

    override fun findAllNowPlayingWithMovieAndTheater(currentTime: LocalDateTime): Map<Movie, List<Screening>> {
        val movies = movieJpaRepository.findMoviesNowPlaying(currentTime, ScreeningStatus.SCHEDULED).map { it.toDomain() }

        return movies.associateWith { movie ->
            screeningJpaRepository.findScreeningsByMovieId(
                movieId = movie.id,
                currentTime = currentTime,
                status = ScreeningStatus.SCHEDULED
            ).map { it.toDomain() }
        }
    }
}