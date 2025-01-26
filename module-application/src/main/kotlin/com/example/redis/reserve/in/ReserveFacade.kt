package com.example.redis.reserve.`in`

import com.example.redis.movie.Reservation
import com.example.redis.movie.event.ReserveEvent
import com.example.redis.movie.`in`.MovieUseCase
import org.apache.catalina.core.ApplicationPushBuilder
import org.springframework.context.ApplicationEventPublisher

class ReserveFacade(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val movieService: MovieUseCase
) {

    fun reserve(movieId: Long, reservation: Reservation) {
        val reservationReceipt = movieService.reserve(movieId, reservation)
        val reserveEvent = ReserveEvent(
            reserveReceiptId = reservationReceipt.reserveReceiptId,
            screening = reservationReceipt.screening,
            seats = reservationReceipt.seats,
            createAt = reservationReceipt.createAt
        )
        applicationEventPublisher.publishEvent(reserveEvent)
    }
}