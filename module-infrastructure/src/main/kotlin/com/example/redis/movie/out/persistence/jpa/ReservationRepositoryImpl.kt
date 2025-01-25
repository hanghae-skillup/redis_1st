package com.example.redis.movie.out.persistence.jpa

import com.example.redis.movie.out.persistence.jpa.QReservationEntity.*
import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory

class ReservationRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): ReservationRepositoryCustom {
    override fun findByUserId(userId: Long): MutableList<ReservationEntity> {
        return queryFactory.selectFrom(reservationEntity)
            .where(reservationEntity.userId.eq(userId))
            .fetch()
    }
}