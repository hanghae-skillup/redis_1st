package com.example.redis.movie.out

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation
import com.example.redis.movie.ReservationReceipt

interface MoviePort {
    fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<Movie>
    fun reserve(reservation: Reservation): ReservationReceipt

    fun findReserveCount(reserveReceiptId: String): Int
}