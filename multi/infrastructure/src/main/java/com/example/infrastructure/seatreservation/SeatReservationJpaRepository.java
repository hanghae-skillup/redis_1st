package com.example.infrastructure.seatreservation;

import com.example.domain.SeatReservation.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatReservationJpaRepository extends JpaRepository<SeatReservation,Long> {
}
