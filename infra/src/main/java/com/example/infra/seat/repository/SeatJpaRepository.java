package com.example.infra.seat.repository;

import com.example.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatJpaRepository extends JpaRepository<Seat,Long> {
}
