package com.movie.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid input value"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", "Internal server error"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Entity not found"),

    // Rate Limit
    IP_RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "R001", "IP rate limit exceeded"),
    USER_RESERVATION_RATE_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "R002", "User reservation rate limit exceeded"),

    // Business
    SEAT_ALREADY_RESERVED(HttpStatus.CONFLICT, "B001", "Seat is already reserved"),
    INVALID_RESERVATION_STATUS(HttpStatus.BAD_REQUEST, "B002", "Invalid reservation status");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
} 