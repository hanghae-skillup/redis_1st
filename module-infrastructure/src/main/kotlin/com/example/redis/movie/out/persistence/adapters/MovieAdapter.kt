package com.example.redis.movie.out.persistence.adapters

import com.example.redis.movie.Movie
import com.example.redis.movie.Reservation
import com.example.redis.movie.out.mapper.MoviePersistenceMapper
import com.example.redis.movie.out.movie.MoviePort
import com.example.redis.movie.out.persistence.jpa.*
import com.example.redis.theater.out.persistence.adapters.TheaterAdapter
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import javax.swing.text.html.Option
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

@Service
class MovieAdapter(
    private val movieRepository: MovieRepository,
    private val screeningRepository: ScreeningRepository,
    private val reservationRepository: ReservationRepository,
    private val theaterAdapter: TheaterAdapter,
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
    override fun reserve(reservation: Reservation): String {
        val seats = reservation.extractSeats()
        val findSeats = theaterAdapter.findSeatsByIds(seats)
        if(findSeats.size != seats.size) {
            throw IllegalStateException()
        }

        val existReservations = this.reservationRepository.findByUserId(reservation.userId)
        reservation.IsLimit(existReservations.size)

        findById(reservation.movieId)
        val screening = findScreeningById(reservation.screeningId)
        val saveReservations = findSeats.stream().map { ReservationEntity.fromDomain(reservation, screening, it) }.toList()
        this.reservationRepository.saveAll(saveReservations)
        return reservation.reserveGroupId
    }
}