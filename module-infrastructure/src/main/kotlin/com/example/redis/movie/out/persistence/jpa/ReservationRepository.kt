package com.example.redis.movie.out.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository: JpaRepository<ReservationEntity, Long>, ReservationRepositoryCustom{
}