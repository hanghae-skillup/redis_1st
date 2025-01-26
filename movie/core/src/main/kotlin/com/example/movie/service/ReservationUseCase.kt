package com.example.movie.service

import com.example.movie.domain.reservation.model.Reservation

interface ReservationUseCase {
    fun reserve(userId: Long, screeningId: Long, requestSeatIds:List<Long>): List<Reservation>
}