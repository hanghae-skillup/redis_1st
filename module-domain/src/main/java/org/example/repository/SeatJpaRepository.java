package org.example.repository;

import org.example.domain.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
}
