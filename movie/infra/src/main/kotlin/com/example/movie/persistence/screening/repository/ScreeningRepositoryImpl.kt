package com.example.movie.persistence.screening.repository

import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.repository.ScreeningRepository
import org.springframework.stereotype.Repository

@Repository
class ScreeningRepositoryImpl(
    private val screeningJpaRepository: ScreeningJpaRepository
) : ScreeningRepository {
    override fun findById(id: Long): Screening? {
        return screeningJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun findByIdWithLock(id: Long): Screening? {
        return screeningJpaRepository.findByIdWithLock(id)?.toDomain()
    }
}