package com.example.redis.movie.out.persistence.jpa

interface ReservationRepositoryCustom {

    fun findByUserId(userId: Long): MutableList<ReservationEntity>
}