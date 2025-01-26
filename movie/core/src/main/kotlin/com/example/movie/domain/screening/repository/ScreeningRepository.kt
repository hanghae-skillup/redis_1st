package com.example.movie.domain.screening.repository

import com.example.movie.domain.screening.model.Screening

interface ScreeningRepository {
    fun findById(id: Long): Screening?
    fun findByIdWithLock(id: Long): Screening?
    fun findByIdWithOptimisticLock(id: Long): Screening?
    fun save(screening: Screening): Screening
}