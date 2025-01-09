package com.example.moviecore.domain.movie.repository

import com.example.moviecore.domain.movie.model.Genre

interface GenreRepository {
    fun findById(id: Long): Genre?
    fun findByName(name: String): Genre?
    fun findAll(): List<Genre>
}