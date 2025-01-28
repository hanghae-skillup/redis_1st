package com.example.movie.domain.screening.model

import com.example.movie.domain.common.model.BaseModel
import com.example.movie.domain.movie.model.Movie
import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.domain.theater.model.Theater
import java.time.LocalDateTime

class Screening(
    val id: Long,
    val movieId: Long,
    val theater: Theater,
    val screeningTime: LocalDateTime,
    val screeningEndTime: LocalDateTime,
    val status: ScreeningStatus,
    var remainingSeatCount: Int,
    override val createdBy: String,
    override val createdAt: LocalDateTime,
    override var updatedBy: String,
    override var updatedAt: LocalDateTime,
) : BaseModel {
    fun reserveSeats(reservedSeats: List<Reservation>, currentTime: LocalDateTime) {
        if(remainingSeatCount < reservedSeats.size) {
            throw IllegalArgumentException("남은 좌석 이상 예매할 수 없습니다")
        }
        remainingSeatCount -= reservedSeats.size
        super.update("user", currentTime)
    }
}