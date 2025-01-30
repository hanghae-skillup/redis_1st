package com.example.app.booking.port;

import com.example.app.booking.domain.Seat;
import com.example.app.booking.dto.SearchSeatCommand;

import java.util.List;

public interface LoadSeatPort {

    List<Seat> loadAllSeats(SearchSeatCommand searchSeatCommand);

    List<Seat> loadAllSeatsByBookingIds(List<Long> bookingIds);
}
