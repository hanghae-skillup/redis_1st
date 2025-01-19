package com.example.movie.persistence.movie.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.screening.model.ScreeningStatus
import com.example.movie.persistence.movie.projection.MovieDtoMapper
import com.example.movie.persistence.movie.projection.toMovieCacheDtos
import com.example.movie.persistence.movie.projection.toMovies
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
@Profile("local-redis", "local-caffeine", "prod")
class CacheMovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository,
) : MovieRepository {
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllByOrderByReleaseDateDesc().map { it.toDomain() }
    }

    @Cacheable(
        value = ["movies"],
        key = "'movies:' + #status + ':' + #title + ':' + #genreId",
        unless = "#result.isEmpty()"
    )
    override fun findAllByStatusWithMovieAndTheater(currentTime: LocalDateTime, status: ScreeningStatus, title: String?, genreId: Long?): List<Movie> {
        val movies = movieJpaRepository.findMoviesByCurrentTimeAndStatusAndTitleAndGenre(currentTime, status, title, genreId)

        val movieIds = movies.map { it.id }
        val screenings = movieJpaRepository.findScreeningsByMovieIds(movieIds, currentTime, status)

        val screeningsByMovieId = screenings.groupBy { it.movieId }

        return movies.map { movie ->
            MovieDtoMapper.toMovie(movie, screeningsByMovieId[movie.id] ?: emptyList())
        }.toMovieCacheDtos().toMovies()
    }
}