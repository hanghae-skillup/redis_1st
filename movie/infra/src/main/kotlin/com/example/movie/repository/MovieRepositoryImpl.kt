package com.example.movie.repository

import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.movie.repository.MovieRepository
import org.springframework.stereotype.Repository

@Repository
class MovieRepositoryImpl(
    private val movieJpaRepository: MovieJpaRepository
) : MovieRepository {
    override fun findById(id: Long): Movie? {
        return movieJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findAllOrderByReleaseDateDesc(): List<Movie> {
        return movieJpaRepository.findAllByOrderByReleaseDateDesc().map { it.toDomain() }
    }
}