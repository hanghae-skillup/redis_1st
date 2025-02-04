package com.movie.infra.repository;

import com.movie.domain.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatJpaRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTheaterId(Long theaterId);
} 