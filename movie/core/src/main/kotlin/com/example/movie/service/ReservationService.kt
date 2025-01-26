package com.example.movie.service

import com.example.movie.domain.common.TimeHandler
import com.example.movie.domain.movie.repository.MovieRepository
import com.example.movie.domain.reservation.exception.ReservationException
import com.example.movie.domain.reservation.model.Reservation
import com.example.movie.domain.reservation.repository.ReservationRepository
import com.example.movie.domain.screening.repository.ScreeningRepository
import com.example.movie.domain.seat.model.Seat
import com.example.movie.domain.seat.repository.SeatRepository
import com.example.movie.message.MessagePublisher
import com.example.movie.message.ReservationCompleteMessage
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val screeningRepository: ScreeningRepository,
    private val seatRepository: SeatRepository,
    private val movieRepository: MovieRepository,
    private val timeHandler: TimeHandler,
    private val messagePublisher: MessagePublisher
) : ReservationUseCase {
    @Transactional
    override fun reserve(userId: Long, screeningId: Long, requestSeatIds:List<Long>) : List<Reservation> {
        val currentTime = timeHandler.getCurrentTime()

        val screening = screeningRepository.findById(screeningId)
            ?: throw ReservationException("상영 정보를 찾을 수 없습니다")

        val movie = movieRepository.findById(screening.movieId)
            ?: throw ReservationException("영화 정보를 찾을 수 없습니다")

        val existingReservations = reservationRepository
            .findByScreeningIdAndUserId(screening.id, userId)

        if (existingReservations.size + requestSeatIds.size > 5) {
            throw throw ReservationException("한 상영당 5개 이상의 예약을 할 수 없습니다")
        }

        val seats = seatRepository.findAllByIdIn(requestSeatIds)
        validateSeats(seats)

        val reservedSeats = reservationRepository
            .findByScreeningAndSeats(screening, seats)
        if (reservedSeats.isNotEmpty()) {
            throw ReservationException("이미 예약된 좌석입니다")
        }

        val reservations = seats.map { seat ->
            Reservation(
                seat = seat,
                screening = screening,
                userId = userId,
                reservationTime = currentTime,
                createdBy = userId.toString(),
                createdAt = currentTime,
                updatedBy = userId.toString(),
                updatedAt = currentTime
            )
        }

        val saveResults = reservationRepository.saveAll(reservations)

        messagePublisher.sendReservationCompleteMessage(
            ReservationCompleteMessage(
                userId = userId.toString(),
                movieTitle = movie.title,
                screeningTime = screening.screeningTime,
                seats = seats
            )
        )
        return saveResults
    }

    private fun validateSeats(seats: List<Seat>) {
        if (!areSeatsContiguous(seats)) {
            throw ReservationException("연속된 좌석만 예약할 수 있습니다")
        }
    }

    private fun areSeatsContiguous(seats: List<Seat>): Boolean {
        if (seats.map { it.row }.distinct().size != 1) return false
        val cols = seats.map { it.col }.sorted()
        return cols.zipWithNext().all { (a, b) -> b - a == 1 }
    }
}