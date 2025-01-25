package com.example.redis.theater.out.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<SeatEntity, Long> {
}