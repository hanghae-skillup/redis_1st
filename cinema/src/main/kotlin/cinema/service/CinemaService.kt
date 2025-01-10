package com.redis.cinema.service

import com.redis.cinema.domain.Cinema
import com.redis.cinema.domain.CinemaRepository
import com.redis.cinema.port.CinemaPort
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