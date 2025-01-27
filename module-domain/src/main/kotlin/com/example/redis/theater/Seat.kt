package com.example.redis.theater

class Seat(
    val seatId: Long,
    val seatRow: String,
    val seatCol: String,
) {
    fun isSameRow(otherSeat: Seat): Boolean {
        return seatRow == otherSeat.seatRow
    }
}