package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;
import com.movie.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByScheduleAndSeat(Schedule schedule, Seat seat);
    List<Reservation> findByUser(User user);
    Optional<Reservation> findByReservationNumber(String reservationNumber);
} 