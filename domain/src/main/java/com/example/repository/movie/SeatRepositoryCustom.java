package com.example.repository.movie;

import com.example.entity.movie.Seat;

import java.util.List;

public interface SeatRepositoryCustom {
    List<Seat> findAllByIdsWithLock(List<Long> ids);
}
