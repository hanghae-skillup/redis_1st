package com.movie.domain.repository;

import com.movie.domain.entity.Schedule;
import com.movie.domain.entity.Seat;

import java.util.List;

public interface SeatRepositoryCustom {
    List<Seat> findReservedSeats(Schedule schedule);
} 