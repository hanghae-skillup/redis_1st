package com.example.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 영화 정보 관련 에러
    INVALID_SEAT_NUMBER("Seat row must be between A-E and column must be between 1-5.", 400),
    INVALID_TITLE_LENGTH("Movie title cannot be more than 50 characters.", 400),
    INVALID_CONTENT_RATING( "Content rating must be provided.", 400),
    INVALID_GENRE("Genre cannot be empty.", 400),
    INVALID_MOVIE("Movie must be provided.", 400),
    INVALID_THEATER("Theater must be provided.", 400),

    // 예약 관련 에러
    INVALID_SCREENING("Screening not found.", 400),
    INVALID_MEMBER("Member not found.", 400),
    SEAT_NOT_FOUND("Some seats do not exist.", 400),
    MAX_SEATS_EXCEEDED("Cannot reserve more than 5 seats per screening, including previous reservations.", 400),
    SEAT_ALREADY_RESERVED("Some seats are already reserved.", 400),
    SEATS_NOT_CONSECUTIVE("Seats must be consecutive in the same row.", 400),
    DUPLICATE_SEAT_REQUEST("Duplicate seat reservations are not allowed.", 400),
    INVALID_REQUEST("Invalid request.", 400),

    LOCK_ACQUISITION_FAILED("Lock acquisition failed.", 400),
    ;

    private final String message;
    private final int statusCode;

}
