package com.redis.service

import com.redis.domain.Cinema
import com.redis.domain.CinemaRepository
import com.redis.port.CinemaPort
import org.springframework.stereotype.Service

@Service
class CinemaService(
    private val cinemaRepository: CinemaRepository,
): CinemaPort {

    override fun getCinema(cinemaId: Long): Cinema {
        return this.cinemaRepository.findById(cinemaId).orElseThrow {
            throw NoSuchElementException("Cinema with id $cinemaId not found")
        }
    }
    override fun getCinemas(cinemaIds: MutableList<Long>): MutableList<Cinema> {
        return this.cinemaRepository.findAllById(cinemaIds)
    }
}