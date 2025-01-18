package com.example.demo.application.service

import com.example.demo.application.dto.MovieDto
import com.example.demo.application.dto.ScreeningDto
import com.example.demo.domain.repository.MovieRepository
import com.example.demo.domain.repository.ScreeningRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class MovieService(
    private val movieRepository: MovieRepository,
    private val screeningRepository: ScreeningRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun getNowShowingMovies(title: String?, genre: String?): List<MovieDto> {
        val movies = movieRepository.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
            title = title ?: "",
            genre = genre ?: ""
        )
        val screenings = screeningRepository.findAll().sortedBy { it.startTime }
        return screenings.groupBy { it.movie }
            .filterKeys { it in movies }
            .map { (movie, screenings) ->
                MovieDto(
                    title = movie.title,
                    rating = movie.rating.name,
                    releaseDate = movie.releaseDate,
                    thumbnailUrl = movie.thumbnailUrl,
                    runningTime = movie.runningTime,
                    genre = movie.genre,
                    screenings = screenings.map {
                        ScreeningDto(
                            cinemaName = it.cinemaName,
                            startTime = it.startTime
                        )
                    }
                )
            }
            .sortedByDescending { it.releaseDate }
    }

    fun getNowShowingMoviesWithCaching(title: String?, genre: String?): List<MovieDto> {
        val cacheKey = "movies::${title ?: ""}::${genre ?: ""}"
        val ops = redisTemplate.opsForValue()

        @Suppress("UNCHECKED_CAST")
        val cachedResult = ops.get(cacheKey) as? List<MovieDto>
        if (cachedResult != null) {
            return cachedResult
        }

        val movies = getNowShowingMovies(title, genre)
        ops.set(cacheKey, movies, 10, TimeUnit.MINUTES)
        return movies
    }
} 