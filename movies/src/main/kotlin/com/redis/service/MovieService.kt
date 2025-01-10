package com.redis.service

import com.redis.port.CinemaPort
import com.redis.controller.dto.MovieResponse
import com.redis.domain.MovieRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(
    private val cinemaPort: CinemaPort,
    private val moviesRepository: MovieRepository
) {

    @Transactional(readOnly = true)
    fun gets(): MutableList<MovieResponse> {
        val movies = moviesRepository.findByOrderByReleaseDateDesc()
        val cinemas = cinemaPort.getCinemas(movies.stream().map { it -> it.cinemaId }.toList())

        return movies.stream().map {
            val cinema = cinemas.stream().filter { c -> c.id == it.cinemaId }.findFirst().orElseThrow()
            MovieResponse.fromEntity(it, cinema)
        }.toList()
    }
}