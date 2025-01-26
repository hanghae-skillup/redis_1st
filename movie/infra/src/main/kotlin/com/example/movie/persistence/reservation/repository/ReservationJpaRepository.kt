package com.example.movie.persistence.reservation.repository

import com.example.movie.persistence.reservation.entity.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ReservationJpaRepository : JpaRepository<ReservationEntity, Long> {
    @Query("SELECT r FROM ReservationEntity r WHERE r.screening.id = :screeningId AND r.seat.id IN :seatIds")
    fun findByScreeningIdAndSeatIds(
        @Param("screeningId") screeningId: Long,
        @Param("seatIds") seatIds: List<Long>
    ): List<ReservationEntity>

    fun findByScreeningIdAndUserId(screeningId: Long, userId: Long): List<ReservationEntity>
}