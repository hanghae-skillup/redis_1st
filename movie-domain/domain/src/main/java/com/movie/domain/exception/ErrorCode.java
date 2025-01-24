package com.movie.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 202
    CONTENT_NOT_FOUND(HttpStatus.ACCEPTED, "CONTENT_NOT_FOUND"),
    RESERVATION_LIMIT_EXCEEDED(HttpStatus.ACCEPTED, "RESERVATION_LIMIT_EXCEEDED"),


    ;

    private final HttpStatus httpStatus;
    private final String message;

}
