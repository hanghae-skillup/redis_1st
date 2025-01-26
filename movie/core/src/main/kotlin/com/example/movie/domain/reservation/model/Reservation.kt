package com.example.movie.domain.reservation.model

import com.example.movie.domain.common.model.BaseModel
import com.example.movie.domain.screening.model.Screening
import com.example.movie.domain.seat.model.Seat
import java.time.LocalDateTime

class Reservation(
    val id: Long = 0,
    val seat: Seat,
    val screening: Screening,
    val userId: Long,
    val reservationTime: LocalDateTime,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override val updatedBy: String,
    override val updatedAt: LocalDateTime
) : BaseModel {
}