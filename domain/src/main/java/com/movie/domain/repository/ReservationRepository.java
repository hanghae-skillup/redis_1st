package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByScheduleIdAndSeatId(Long scheduleId, Long seatId);
    long countByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);
    List<Reservation> findByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);
    List<Reservation> findByUserId(Long userId);
    Optional<Reservation> findByReservationNumber(String reservationNumber);
} 