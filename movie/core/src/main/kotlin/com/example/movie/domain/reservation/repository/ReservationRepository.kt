package com.example.movie.domain.reservation.repository

import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.seat.model.Seat

interface ReservationRepository {
    fun findByScreeningAndSeats(screening: Screening, seats: List<Seat>) : List<Reservation>
    fun findByScreeningIdAndUserId(screeningId: Long, userId: Long): List<Reservation>
    fun saveAll(reservations: List<Reservation>): List<Reservation>
    fun findByScreeningAndSeatsWithLock(screening: Screening, seats: List<Seat>): List<Reservation>
}