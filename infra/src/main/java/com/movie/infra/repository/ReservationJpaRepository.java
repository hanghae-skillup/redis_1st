package com.movie.infra.repository;

import com.movie.domain.entity.Reservation;
import com.movie.domain.repository.ReservationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long>, ReservationRepository {
    @Override
    default Reservation findById(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    List<Reservation> findByUserId(Long userId);

    @Override
    List<Reservation> findByScheduleId(Long scheduleId);

    @Override
    List<Reservation> findBySeatId(Long seatId);
} 