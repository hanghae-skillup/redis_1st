package com.example.redis.movie.out.persistence.adapters

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation
import com.example.redis.movie.ReservationReceipt
import com.example.redis.movie.out.mapper.MoviePersistenceMapper
import com.example.redis.movie.out.MoviePort
import com.example.redis.movie.out.persistence.jpa.*
import com.example.redis.reserve.out.persistence.jpa.ReservationRepository
import com.example.redis.theater.out.mapper.TheaterPersistenceMapper
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class MovieAdapter(
    private val movieRepository: MovieRepository,
    private val screeningRepository: ScreeningRepository,
    private val reservationRepository: ReservationRepository,
): MoviePort {
    override fun findByOrderByReleaseDateDesc(title: String?, genre: String?): MutableList<Movie> {
        val movies = this.movieRepository.findByOrderByReleaseDateDesc(title, genre)
        return movies
            .stream()
            .map { MoviePersistenceMapper.toMovieDomain(it) }.toList()
    }
    fun findById(movieId: Long): MovieEntity {
        return this.movieRepository.findById(movieId)
            .orElseThrow { NotFoundException() }
    }

    fun findScreeningById(screeningId: Long): ScreeningEntity {
        return this.screeningRepository.findById(screeningId)
            .orElseThrow { NotFoundException() }
    }
    
    //TODO: update 방식으로 변경
    override fun reserve(reservation: Reservation): ReservationReceipt {
        // 영화 검증
        findById(reservation.movieId)

        val seatIds = reservation.extractSeats().stream().map { it.seatId }.toList()
        val findReservations = this.reservationRepository.findSeatsByIds(reservation.screeningId, seatIds)
        if(findReservations.size != seatIds.size) {
            throw IllegalStateException()
        }

        val existReservations = this.reservationRepository.findByUserId(reservation.screeningId, reservation.userId)
        reservation.isLimit(existReservations.size)

        val screening = findScreeningById(reservation.screeningId)
        findReservations.forEach {
            it.reserveReceiptId = reservation.reserveReceiptId
            it.userId = reservation.userId
        }

        this.reservationRepository.saveAll(findReservations)

        return ReservationReceipt(
            reservation.reserveReceiptId,
            MoviePersistenceMapper.toScreeningDomain(screening),
            findReservations.stream()
                .map { TheaterPersistenceMapper.toSeatDomain(it.seat) }
                .toList()
        )
    }
}