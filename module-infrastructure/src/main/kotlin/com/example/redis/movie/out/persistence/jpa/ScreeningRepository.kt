package com.example.redis.movie.out.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ScreeningRepository: JpaRepository<ScreeningEntity, Long> {
}