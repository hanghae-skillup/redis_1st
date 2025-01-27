package com.example.app.booking.port;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.dto.SearchBookingCommand;

import java.util.List;

public interface LoadBookingPort {
    List<Booking> loadAllBookings(SearchBookingCommand searchBookingCommand);
}
