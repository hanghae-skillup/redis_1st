package com.example.movie.dto

import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.domain.seat.model.Seat
import java.time.LocalDateTime

data class ReservationResponse (
    val id: Long,
    val seat: SeatRespose,
    val screening: ScreeningResponse,
    val userId: Long,
    val reservationTime: LocalDateTime,
) {
    companion object {
        fun from(reservation: Reservation): ReservationResponse {
            return ReservationResponse(
                id = reservation.id,
                seat = SeatRespose.from(reservation.seat),
                screening = ScreeningResponse.from(reservation.screening),
                userId = reservation.userId,
                reservationTime = reservation.reservationTime,
            )
        }
    }
}

data class SeatRespose (
    val id: Long,
    val theater: TheaterResponse,
    val row: Char,
    val col: Int,
) {
    companion object {
        fun from(seat: Seat): SeatRespose {
            return SeatRespose(
                id = seat.id,
                theater = TheaterResponse.from(seat.theater),
                row = seat.row,
                col = seat.col,
            )
        }
    }
}