package com.movie.domain.repository;

import com.movie.domain.entity.Reservation;

import java.util.List;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Reservation findById(Long id);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByScheduleId(Long scheduleId);
    List<Reservation> findBySeatId(Long seatId);
} 