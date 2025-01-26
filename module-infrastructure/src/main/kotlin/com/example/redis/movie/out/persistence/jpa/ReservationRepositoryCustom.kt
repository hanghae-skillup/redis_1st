package com.example.redis.movie.out.persistence.jpa

import com.example.redis.theater.out.persistence.jpa.SeatEntity

interface ReservationRepositoryCustom {

    fun findByUserId(screeningId: Long, userId: Long): MutableList<ReservationEntity>
    fun findSeatsByIds(screeningId: Long, seatIds: MutableList<Long>): MutableList<ReservationEntity>
}