package com.example.redis.reserve.out.persistence.jpa

import com.example.redis.reserve.out.persistence.jpa.QReservationEntity.*
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock

class ReservationRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): ReservationRepositoryCustom {
    override fun findByUserId(screeningId: Long, userId: Long): MutableList<ReservationEntity> {
        return queryFactory.selectFrom(reservationEntity)
            .where(
                reservationEntity.screening.id.eq(screeningId),
                reservationEntity.userId.eq(userId))
            .fetch()
    }

    override fun findSeatsByIds(screeningId: Long, seatIds: MutableList<Long>): MutableList<ReservationEntity> {
        return queryFactory.select(reservationEntity)
            .from(reservationEntity)
            .where(
                reservationEntity.seat.id.`in`(seatIds),
                reservationEntity.screening.id.eq(screeningId),
                reservationEntity.reserveReceiptId.isNull
            )
            .setLockMode(LockModeType.OPTIMISTIC) // 비관적 락 설정
            .fetch()
    }

    override fun findCountByReserveReceiptId(reserveReceipt: String): Int {
        return queryFactory.select(reservationEntity)
            .from(reservationEntity)
            .where(
                reservationEntity.reserveReceiptId.eq(reserveReceipt)
            ).fetch().size
    }
}