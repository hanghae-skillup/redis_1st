package com.example.redis.movie.`in`

import com.example.redis.movie.MovieAdapter
import com.example.redis.movie.Movie
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieAdapter: MovieAdapter,
): MovieUseCase {
    override fun gets(): MutableList<Movie> {
        val movies = movieAdapter.findByOrderByReleaseDateDesc()
        val cinemas = cinemaPort.getCinemas(movies.stream().map { it -> it.cinemaId }.toList())

        return movies.stream().map {
            val cinema = cinemas.stream().filter { c -> c.id == it.cinemaId }.findFirst().orElseThrow()
            MovieResponse.fromEntity(it, cinema)
        }.toList()

    }
}