package com.example.redis.movie.`in`.movie

import com.example.redis.movie.command.Movie
import com.example.redis.movie.out.movie.MoviePort
import com.example.redis.movie.query.MovieProjection
import org.springframework.stereotype.Service

@Service
class MovieService(
    private val movieAdapter: MoviePort,
): MovieUseCase {
    override fun gets(title: String?, genre: String?): MutableList<MovieProjection> {
        val movies = movieAdapter.findByOrderByReleaseDateDesc(title, genre)
        return movies
    }
}