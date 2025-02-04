package com.example.app.booking.presentation.util;

import com.example.app.booking.presentation.dto.request.CreateBookingRequest;

import java.time.format.DateTimeFormatter;

public class BookingKeyGenerator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String generateLockKey(CreateBookingRequest createBookingRequest) {
        return String.format("BOOKING:%d:%d:%d:%s:%c",
                createBookingRequest.movieId(),
                createBookingRequest.showtimeId(),
                createBookingRequest.theaterId(),
                createBookingRequest.bookingDate().format(DATE_FORMATTER),
                createBookingRequest.seats().getFirst().charAt(0));
    }

    public static String generateRateLimitKey(CreateBookingRequest createBookingRequest) {
        return String.format("BOOKING:RATE_LIMIT:%d:%d:%d:%d:%s",
                createBookingRequest.userId(),
                createBookingRequest.movieId(),
                createBookingRequest.showtimeId(),
                createBookingRequest.theaterId(),
                createBookingRequest.bookingDate().format(DATE_FORMATTER));
    }
}
