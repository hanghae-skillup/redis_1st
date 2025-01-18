package com.example.movie.persistence.movie.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.common.MovieProjectionMapper.toDomain
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class MovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository,
) : MovieRepository {
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllByOrderByReleaseDateDesc().map { it.toDomain() }
    }

    override fun findAllByStatusWithMovieAndTheater(currentTime: LocalDateTime, status: ScreeningStatus, title: String?, genreId: Long?): List<Movie> {
        return movieJpaRepository
            .findMoviesNowPlaying(currentTime, status, title, genreId)
            .map { it.toDomain() }
    }
}