package com.example.redis.movie

import com.example.redis.adapters.movie.persistence.MovieAdapter
import com.example.redis.movie.Movie
import com.example.redis.movie.`in`.movie.MovieUseCase
import com.example.redis.movie.out.movie.MoviePort
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieAdapter: MoviePort,
): MovieUseCase {
    override fun gets(): MutableList<Movie> {
        val movies = movieAdapter.findByOrderByReleaseDateDesc()
        return movies
    }
}