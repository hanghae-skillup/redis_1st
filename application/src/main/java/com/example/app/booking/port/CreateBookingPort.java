package com.example.app.booking.port;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.dto.CreateBookingCommand;

public interface CreateBookingPort {
    Booking saveBooking(CreateBookingCommand createBookingCommand);
}
