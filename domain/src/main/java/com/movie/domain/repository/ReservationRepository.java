package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.ReservationStatus;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    boolean existsByScheduleIdAndSeatIdAndStatus(Long scheduleId, Long seatId, ReservationStatus status);
    long countByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);
    List<Reservation> findByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);
} 