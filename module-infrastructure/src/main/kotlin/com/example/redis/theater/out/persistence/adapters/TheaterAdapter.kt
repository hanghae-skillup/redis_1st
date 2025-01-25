package com.example.redis.theater.out.persistence.adapters

import com.example.redis.movie.out.movie.TheaterPort
import com.example.redis.theater.Seat
import com.example.redis.theater.out.mapper.TheaterPersistenceMapper
import com.example.redis.theater.out.persistence.jpa.SeatEntity
import com.example.redis.theater.out.persistence.jpa.SeatRepository
import com.example.redis.theater.out.persistence.jpa.TheaterRepository
import org.springframework.stereotype.Service

@Service
class TheaterAdapter(
    private val theaterRepository: TheaterRepository,
    private val seatRepository: SeatRepository
): TheaterPort {

    fun findSeatsByIds(seats: MutableList<Seat>): MutableList<SeatEntity> {
        return this.seatRepository.findAllById(seats.stream().map { it.seatId }.toList())
    }
}