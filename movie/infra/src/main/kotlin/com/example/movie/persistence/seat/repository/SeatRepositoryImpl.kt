package com.example.movie.persistence.seat.repository

import com.example.movie.domain.seat.model.Seat
import com.example.movie.domain.seat.repository.SeatRepository
import org.springframework.stereotype.Repository

@Repository
class SeatRepositoryImpl(
    private val seatJpaRepository: SeatJpaRepository
) : SeatRepository {
    override fun findAllByIdIn(seatIds: Collection<Long>): List<Seat> {
        val seatEntities = seatJpaRepository.findAllByIdIn(seatIds)
        return seatEntities.map{ it.toDomain()}
    }
}