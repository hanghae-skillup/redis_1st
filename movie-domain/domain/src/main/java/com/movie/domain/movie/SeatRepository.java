package com.movie.domain.movie;

import com.movie.domain.movie.domain.Seat;

import java.util.List;

public interface SeatRepository {

    List<Seat> getSeats(List<Long> seatIds);

}
