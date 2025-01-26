package com.example.redis.movie

import com.example.redis.theater.Seat
import java.time.LocalDateTime

class ReservationReceipt(
    val reserveReceiptId: String,
    val screening: Screening,
    val seats: MutableList<Seat> = mutableListOf(),
    val createAt: LocalDateTime = LocalDateTime.now(),
) { }