package com.example.movie.domain.theater.repository

import com.example.movie.domain.theater.model.Theater

interface TheaterRepository {
    fun findById(id: Long): Theater?
}