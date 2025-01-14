package com.example.redis.movie

import com.example.redis.movie.out.movie.MoviePort

class MovieAdapter(
    private val movieRepository: MovieRepository
): MoviePort {
    fun findByOrderByReleaseDateDesc(): MutableList<MovieEntity> {
        val movies = this.movieRepository.findByOrderByReleaseDateDesc()
        
//        movies.stream().map {
//        }
    }
}