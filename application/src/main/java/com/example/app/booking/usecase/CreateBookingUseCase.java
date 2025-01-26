package com.example.app.booking.usecase;

import com.example.app.booking.domain.Booking;
import com.example.app.booking.dto.CreateBookingCommand;

public interface CreateBookingUseCase {
    Booking createBooking(String lockKey, CreateBookingCommand createBookingCommand);
}
