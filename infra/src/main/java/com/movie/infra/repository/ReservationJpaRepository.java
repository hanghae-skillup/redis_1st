package com.movie.infra.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.entity.ReservationStatus;
import com.movie.domain.repository.ReservationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long>, ReservationRepository {
    boolean existsByScheduleIdAndSeatIdAndStatus(Long scheduleId, Long seatId, ReservationStatus status);

    long countByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);

    List<Reservation> findByUserIdAndScheduleIdAndStatus(Long userId, Long scheduleId, ReservationStatus status);

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByScheduleId(Long scheduleId);

    List<Reservation> findBySeatId(Long seatId);
} 