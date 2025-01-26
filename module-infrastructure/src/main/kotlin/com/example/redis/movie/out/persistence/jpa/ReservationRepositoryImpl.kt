package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.out.persistence.jpa.QReservationEntity.*
import com.example.redis.theater.Seat
import com.example.redis.theater.out.persistence.jpa.SeatEntity
import com.querydsl.core.QueryFactory
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
            .fetch()
    }
}