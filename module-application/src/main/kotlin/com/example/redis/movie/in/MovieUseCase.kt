package com.example.redis.movie.`in`

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation
import com.example.redis.movie.ReservationReceipt

interface MovieUseCase {

    fun gets(title: String?, genre: String?): MutableList<Movie>

    fun findReserveCount(reserveReceiptId: String): Int
    fun reserve(movieId: Long, reservation: Reservation): ReservationReceipt
}