package com.example.movie.domain.seat.repository

import com.example.movie.domain.seat.model.Seat

interface SeatRepository {
    fun findAllByIdIn(seatIds: Collection<Long>): List<Seat>
}