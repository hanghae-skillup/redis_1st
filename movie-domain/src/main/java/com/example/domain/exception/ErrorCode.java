package com.example.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 영화 정보 관련 에러
    INVALID_TITLE_LENGTH("Movie title cannot be more than 50 characters.", 400),
    INVALID_CONTENT_RATING( "Content rating must be provided.", 400),
    INVALID_GENRE("Genre cannot be empty.", 400),
    INVALID_MOVIE("Movie must be provided.", 400),
    INVALID_THEATER("Theater must be provided.", 400),

    // 예약 관련 에러
    INVALID_SCREENING("Screening not found.", 400),
    INVALID_MEMBER("Member not found.", 400),
    MAX_SEATS_EXCEEDED("Cannot reserve more than 5 seats per screening, including previous reservations.", 400),
    SEAT_ALREADY_RESERVED("Some seats are already reserved.", 400),
    SEATS_NOT_CONSECUTIVE("Seats must be consecutive in the same row.", 400),
    INVALID_REQUEST("Invalid request.", 400),
    RESERVATION_REQUEST_FAILED("Reservation request failed.", 400),

    // 동시성 제어 관련 에러
    OPTIMISTIC_LOCK_CONFLICT("Optimistic lock conflict. Please try again.", 409),
    DISTRIBUTED_LOCK_FAILURE("Distributed lock acquisition failed. Please try again.", 423)
    ;

    private final String message;
    private final int statusCode;

}
