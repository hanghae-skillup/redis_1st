package com.example.app.booking.port;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.domain.Seat;

import java.util.List;

public interface UpdateSeatPort {

    List<Seat> updateAllSeats(List<Seat> seats, Booking booking);
}
