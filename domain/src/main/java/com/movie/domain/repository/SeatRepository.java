package com.movie.domain.repository;

import com.movie.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long>, SeatRepositoryCustom {
} 