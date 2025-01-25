package com.example.app.booking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookingErrorMessage {

    SEAT_ROW_NOT_IN_SEQUENCE(HttpStatus.BAD_REQUEST, "열(row)이 다른 자리가 있어요"),
    SEAT_ALREADY_OCCUPIED(HttpStatus.BAD_REQUEST, "이미 예약된 자리에요"),
    OVER_MAX_LIMIT_SEATS(HttpStatus.BAD_REQUEST, "최대 5자리 예약이 가능해요");

    private final HttpStatus httpStatus;
    private final String message;
}
