package com.movie.domain.repository;

import com.movie.domain.entity.Seat;
import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    Seat save(Seat seat);
    List<Seat> findByTheaterId(Long theaterId);
    Optional<Seat> findById(Long id);
} 