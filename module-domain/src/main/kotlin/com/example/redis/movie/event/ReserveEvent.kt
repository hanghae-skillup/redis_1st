package com.example.redis.movie.event

import com.example.redis.movie.Screening
import com.example.redis.theater.Seat
import java.time.LocalDateTime

data class ReserveEvent(
    val reserveReceiptId: String,
    val screening: Screening,
    val seats: MutableList<Seat>,
    val createAt: LocalDateTime,
) { }