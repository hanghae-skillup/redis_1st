package com.example.movie.persistence.seat.repository

import com.example.movie.persistence.seat.entity.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatJpaRepository : JpaRepository<SeatEntity, Long> {
    fun findAllByIdIn(seatIds: Collection<Long>): List<SeatEntity>
}