package com.example.redis.movie.out.persistence

import com.example.redis.movie.Movie
import com.example.redis.movie.out.mapper.MoviePersistenceMapper
import com.example.redis.movie.out.movie.MoviePort

class MovieAdapter(
    private val movieRepository: MovieRepository
): MoviePort {
    override fun findByOrderByReleaseDateDesc(): MutableList<Movie> {
        val movies = this.movieRepository.findByOrderByReleaseDateDesc()
        return movies.stream().map { MoviePersistenceMapper.toMovieDomain(it) }.toList()
    }
}