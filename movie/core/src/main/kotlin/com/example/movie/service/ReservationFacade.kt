package com.example.movie.service

import com.example.movie.lock.aop.DistributedLock
import com.example.movie.domain.reservation.exception.ReservationException
import com.example.movie.domain.reservation.model.Reservation
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service

@Service
class ReservationFacade(
    private val reservationService: ReservationService,
) : ReservationUseCase {
    companion object {
        private const val MAX_RETRY_ATTEMPTS = 3
    }

    @DistributedLock(key = "'lock:reservation:' + #screeningId", waitTime = 2000, leaseTime = 1000)
    override fun reserve(userId: Long, screeningId: Long, requestSeatIds: List<Long>): List<Reservation> {
        var retryCount = 0
        while (retryCount < MAX_RETRY_ATTEMPTS) {
            try {
                return reservationService.reserve(userId, screeningId, requestSeatIds)
            } catch (e: OptimisticLockingFailureException) {
                retryCount++
                if (retryCount >= MAX_RETRY_ATTEMPTS) {
                    throw ReservationException("선택할 수 없는 좌석입니다")
                }
            }
        }
        throw ReservationException("예약 중 오류가 발생했습니다")
    }
}