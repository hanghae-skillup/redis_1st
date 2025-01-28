package com.example.movie.persistence.screening.repository

import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.screening.repository.ScreeningRepository
import com.example.movie.persistence.screening.model.ScreeningEntity
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.orm.ObjectOptimisticLockingFailureException
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

    override fun findByIdWithOptimisticLock(id: Long): Screening? {
        return screeningJpaRepository.findByIdWithOptimisticLock(id)?.toDomain()
    }

    override fun save(screening: Screening): Screening {
        try {
            val entity = if (screening.id > 0) {
                val existingEntity = screeningJpaRepository.findByIdWithOptimisticLock(screening.id)
                    ?: throw IllegalArgumentException("Screening not found (id=${screening.id})")

                existingEntity.updateFromDomain(screening)
                existingEntity
            } else {
                ScreeningEntity.from(screening)
            }

            val savedEntity = screeningJpaRepository.save(entity)
            return savedEntity.toDomain()
        } catch (e: ObjectOptimisticLockingFailureException) {
            throw OptimisticLockingFailureException("Concurrent modification detected")
        }
    }
}