package com.example.redis.theater.out.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TheaterRepository: JpaRepository<TheaterEntity, Long> {
}