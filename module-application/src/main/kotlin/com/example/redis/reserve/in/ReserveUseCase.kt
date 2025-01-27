package com.example.redis.reserve.`in`

import com.example.redis.movie.Reservation

interface ReserveUseCase {
    fun reserve(movieId: Long, reservation: Reservation): String
    fun findReserveCount(reserveReceiptId: String): Int
}
