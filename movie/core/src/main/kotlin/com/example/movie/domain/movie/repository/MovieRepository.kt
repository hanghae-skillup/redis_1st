package com.example.movie.domain.movie.repository

import com.example.movie.domain.movie.model.Movie

interface MovieRepository {
    fun findById(id: Long): Movie?
    fun findAllOrderByReleaseDateDesc(): List<Movie>
}