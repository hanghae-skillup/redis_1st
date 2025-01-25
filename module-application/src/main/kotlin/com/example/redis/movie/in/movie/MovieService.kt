package com.example.redis.movie.`in`.movie

import com.example.redis.movie.Movie
import com.example.redis.movie.out.movie.MoviePort
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(
    private val movieAdapter: MoviePort,
): MovieUseCase {

    @Cacheable("movieSearchCache")
    @Transactional(readOnly = true)
    override fun gets(title: String?, genre: String?): MutableList<Movie> {
        val movies = movieAdapter.findByOrderByReleaseDateDesc(title, genre)
        return movies
    }
}