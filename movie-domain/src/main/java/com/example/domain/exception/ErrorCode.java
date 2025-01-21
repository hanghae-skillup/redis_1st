package com.example.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_SEAT_NUMBER("Seat row must be between A-E and column must be between 1-5.", 400),
    INVALID_TITLE("Movie title cannot be empty.", 400),
    INVALID_TITLE_LENGTH("Movie title cannot be more than 50 characters.", 400),
    INVALID_CONTENT_RATING( "Content rating must be provided.", 400),
    INVALID_GENRE("Genre cannot be empty.", 400),
    INVALID_MOVIE("Movie must be provided.", 400),
    INVALID_THEATER("Theater must be provided.", 400),
    ;

    private final String message;
    private final int statusCode;

}
