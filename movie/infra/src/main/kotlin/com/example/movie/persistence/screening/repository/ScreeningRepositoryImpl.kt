package com.example.movie.persistence.screening.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.domain.screening.repository.ScreeningRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ScreeningRepositoryImpl(
    private val screeningJpaRepository: ScreeningJpaRepository
) : ScreeningRepository {
    override fun findById(id: Long): Screening? {
        return screeningJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAllNowPlayingWithMovieAndTheater(currentTime: LocalDateTime): Map<Movie, List<Screening>> {
        val movies = screeningJpaRepository.findMoviesNowPlaying(currentTime, ScreeningStatus.SCHEDULED).map { it.toDomain() }

        return movies.associateWith { movie ->
            screeningJpaRepository.findScreeningsByMovieId(
                movieId = movie.id,
                currentTime = currentTime,
                status = ScreeningStatus.SCHEDULED
            ).map { it.toDomain() }
        }
    }
}