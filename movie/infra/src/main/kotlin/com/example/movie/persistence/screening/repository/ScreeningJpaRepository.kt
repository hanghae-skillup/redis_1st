package com.example.movie.persistence.screening.repository

import com.example.movie.persistence.screening.model.ScreeningEntity
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ScreeningJpaRepository : JpaRepository<ScreeningEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM ScreeningEntity s WHERE s.id = :id")
    fun findByIdWithLock(@Param("id") id: Long): ScreeningEntity?

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM ScreeningEntity s WHERE s.id = :id")
    fun findByIdWithOptimisticLock(@Param("id") id: Long): ScreeningEntity?
}