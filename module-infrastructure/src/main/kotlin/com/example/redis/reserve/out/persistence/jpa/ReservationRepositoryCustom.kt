package com.example.redis.reserve.out.persistence.jpa

interface ReservationRepositoryCustom {

    fun findByUserId(screeningId: Long, userId: Long): MutableList<ReservationEntity>
    fun findSeatsByIds(screeningId: Long, seatIds: MutableList<Long>): MutableList<ReservationEntity>

    fun findCountByReserveReceiptId(reserveReceipt: String): Int
}