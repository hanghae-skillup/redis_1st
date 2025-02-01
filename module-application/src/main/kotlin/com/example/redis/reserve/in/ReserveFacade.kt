package com.example.redis.reserve.`in`

import com.example.redis.annotations.DistributedLock
import com.example.redis.managers.DistributedLockManager
import com.example.redis.movie.Reservation
import com.example.redis.movie.event.ReserveEvent
import com.example.redis.movie.`in`.MovieUseCase
import org.apache.catalina.core.ApplicationPushBuilder
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Service
class ReserveFacade(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val movieService: MovieUseCase,
    private val distributedLockManager: DistributedLockManager,
): ReserveUseCase {

    //@DistributedLock(lockKey = "reservation")
    @Transactional(readOnly = false)
    override fun reserve(movieId: Long, reservation: Reservation): String {
        val screeningId = reservation.screeningId
        val seatRows = reservation.seats.map { it.seatRow }.distinct().joinToString("")
        val lockKey = "$screeningId:$seatRows"
        val waitTime = 1L
        val leaseTime = 5L

        return distributedLockManager.executeWithLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS) {
            val reservationReceipt = movieService.reserve(movieId, reservation)
            val reserveEvent = ReserveEvent(
                reserveReceiptId = reservationReceipt.reserveReceiptId,
                screening = reservationReceipt.screening,
                seats = reservationReceipt.seats,
                createAt = reservationReceipt.createAt
            )
            applicationEventPublisher.publishEvent(reserveEvent)
            reservationReceipt.reserveReceiptId
        }
    }

    override fun findReserveCount(reserveReceiptId: String): Int {
        return movieService.findReserveCount(reserveReceiptId)
    }
}