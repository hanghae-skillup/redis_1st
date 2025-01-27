package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByScheduleAndSeat(Schedule schedule, Seat seat);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.user JOIN FETCH r.schedule JOIN FETCH r.seat WHERE r.user = :user")
    List<Reservation> findByUser(@Param("user") User user);

    @Query("SELECT r FROM Reservation r JOIN FETCH r.user JOIN FETCH r.schedule JOIN FETCH r.seat WHERE r.reservationNumber = :reservationNumber")
    Optional<Reservation> findByReservationNumber(@Param("reservationNumber") String reservationNumber);
} 