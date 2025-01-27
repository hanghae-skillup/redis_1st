package com.movie.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid input value"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", "Internal server error"),
    
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "User not found"),
    
    // Schedule
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "Schedule not found"),
    
    // Seat
    SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "ST001", "Seat not found"),
    SEAT_ALREADY_RESERVED(HttpStatus.CONFLICT, "ST002", "Seat is already reserved"),
    
    // Reservation
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "Reservation not found"),
    FAILED_TO_ACQUIRE_LOCK(HttpStatus.CONFLICT, "R002", "Failed to acquire lock for reservation");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
} 