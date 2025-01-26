package com.example.domain.reservation.repository;

import com.example.domain.reservation.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat,Long> {
}
