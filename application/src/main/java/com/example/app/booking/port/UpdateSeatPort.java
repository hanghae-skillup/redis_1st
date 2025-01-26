package com.example.app.booking.port;

import com.example.app.booking.domain.Seat;

import java.util.List;

public interface UpdateSeatPort {

    List<Seat> updateAllSeats(List<Long> seatIds, Long bookingId);
}
