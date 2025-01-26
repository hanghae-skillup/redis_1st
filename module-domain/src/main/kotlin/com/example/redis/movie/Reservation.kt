package com.example.redis.movie

import com.example.redis.theater.Seat
import java.util.UUID

data class Reservation(
    val movieId: Long,
    var reserveReceiptId: String = UUID.randomUUID().toString(),
    val screeningId: Long,
    var userId :Long,
    val seats: MutableList<Seat> = mutableListOf()
) {
    val LIMIT = 5
    init {
        validate(seats)
    }
    fun extractSeats(): MutableList<Seat> {
        return seats
    }

    fun isLimit(exist: Int): Boolean {
        if(this.seats.size + exist > LIMIT) {
            throw IllegalStateException()
        }
        return true;
    }

    private fun validate(seats: MutableList<Seat>) {
        //A1 ... A5까지
        //A1, A2, B1, B2
        seats.sortedWith(compareBy({it.seatRow}, {it.seatCol}))
        var previousSeat = seats.first()
        val isSequential: (previous: Seat, current: Seat) -> Boolean = {previous, current ->  previous.seatCol.toInt() + 1 == current.seatCol.toInt() }

        for(i in 1 until seats.size) {
            val currentSeat = seats[i]
            if(!previousSeat.isSameRow(currentSeat) || !isSequential(previousSeat, currentSeat)) {
                throw IllegalStateException()
            }
            previousSeat = currentSeat
        }
    }
}