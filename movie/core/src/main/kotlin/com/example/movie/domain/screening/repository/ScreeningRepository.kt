package com.example.movie.domain.screening.repository

import com.example.movie.domain.screening.model.Screening

interface ScreeningRepository {
    fun findById(id: Long): Screening?
}