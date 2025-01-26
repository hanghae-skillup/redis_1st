package com.example.movie.persistence.reservation.repository

import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.domain.reservation.repository.ReservationRepository
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.seat.model.Seat
import com.example.movie.persistence.reservation.entity.ReservationEntity
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(
    private val reservationJpaRepository: ReservationJpaRepository
) : ReservationRepository {
    override fun findByScreeningAndSeats(screening: Screening, seats: List<Seat>) : List<Reservation> {
        val reservations = reservationJpaRepository.findByScreeningIdAndSeatIds(
            screeningId = screening.id,
            seatIds = seats.map { it.id })
        return reservations.map { it.toDomain() }
    }

    override fun findByScreeningIdAndUserId(screeningId: Long, userId: Long): List<Reservation> {
        return reservationJpaRepository.findByScreeningIdAndUserId(screeningId, userId).map { it.toDomain() }
    }

    override fun saveAll(reservations: List<Reservation>) : List<Reservation> {
        return reservationJpaRepository.saveAll(reservations.map{ ReservationEntity.from(it)}).map { it.toDomain() }
    }
}