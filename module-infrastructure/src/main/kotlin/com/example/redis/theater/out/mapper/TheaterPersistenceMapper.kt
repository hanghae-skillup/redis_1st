package com.example.redis.theater.out.mapper

import com.example.redis.theater.Seat
import com.example.redis.theater.Theater
import com.example.redis.theater.out.persistence.jpa.SeatEntity
import com.example.redis.theater.out.persistence.jpa.TheaterEntity

class TheaterPersistenceMapper {
    companion object {
        fun toSeatDomain(entity: SeatEntity): Seat {
            requireNotNull(entity.id)
            return Seat(
                seatId = entity.id,
                seatRow = entity.row,
                seatCol = entity.col
            )
        }
        fun toTheaterDomain(entity: TheaterEntity): Theater {
            requireNotNull(entity.id)
            return Theater(
                theaterId = entity.id,
                name = entity.name,
            )
        }

    }
}