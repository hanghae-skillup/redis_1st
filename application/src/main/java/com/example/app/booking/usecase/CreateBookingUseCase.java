package com.example.app.booking.usecase;

import com.example.app.booking.dto.CreateBookingCommand;

public interface CreateBookingUseCase {
    void createBooking(CreateBookingCommand createBookingCommand);
}
