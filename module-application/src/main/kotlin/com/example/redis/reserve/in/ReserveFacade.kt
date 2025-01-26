package com.example.redis.reserve.`in`

import com.example.redis.annotations.DistributedLock
import com.example.redis.movie.Reservation
import com.example.redis.movie.event.ReserveEvent
import com.example.redis.movie.`in`.MovieUseCase
import org.apache.catalina.core.ApplicationPushBuilder
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReserveFacade(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val movieService: MovieUseCase
): ReserveUseCase {

    @DistributedLock(lockKey = "reservation")
    @Transactional(readOnly = false)
    override fun reserve(movieId: Long, reservation: Reservation): String {
        val reservationReceipt = movieService.reserve(movieId, reservation)
        val reserveEvent = ReserveEvent(
            reserveReceiptId = reservationReceipt.reserveReceiptId,
            screening = reservationReceipt.screening,
            seats = reservationReceipt.seats,
            createAt = reservationReceipt.createAt
        )
        applicationEventPublisher.publishEvent(reserveEvent)
        return reservationReceipt.reserveReceiptId
    }

    override fun findReserveCount(reserveReceiptId: String): Int {
        return movieService.findReserveCount(reserveReceiptId)
    }
}