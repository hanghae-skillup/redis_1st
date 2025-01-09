package com.redis.movies.service

import com.redis.movies.controller.dto.MovieResponse
import com.redis.movies.domain.Movie
import com.redis.movies.domain.MovieRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(
    private val moviesRepository: MovieRepository
) {

    @Transactional(readOnly = true)
    fun gets(): MutableList<MovieResponse> {
        val movies = moviesRepository.findByReleaseDateOrderByDesc();
        return movies.stream().map { MovieResponse.fromEntity(it)}.toList()
    }
}