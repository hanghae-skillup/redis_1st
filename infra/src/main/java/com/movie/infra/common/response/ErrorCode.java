package com.movie.infra.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid input value"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    
    // RateLimit
    RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded"),
    BOOKING_TIME_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "Booking time limit exceeded for this time slot");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
} 