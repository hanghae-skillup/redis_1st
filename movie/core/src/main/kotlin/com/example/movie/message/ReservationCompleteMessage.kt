package com.example.movie.message

import com.example.movie.domain.seat.model.Seat
import java.time.LocalDateTime

data class ReservationCompleteMessage(
    val userId: String,
    val movieTitle: String,
    val screeningTime: LocalDateTime,
    val seats: List<Seat>
) {
    override fun toString(): String {
        return "ReservationCompleteMessage(userId='$userId', movieTitle='$movieTitle', screeningTime=$screeningTime, seats=$seats)"
    }
}