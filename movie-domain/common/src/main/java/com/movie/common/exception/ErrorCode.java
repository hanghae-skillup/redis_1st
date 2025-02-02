package com.movie.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 202
    CONTENT_NOT_FOUND(HttpStatus.ACCEPTED, "CONTENT_NOT_FOUND"),
    LIMIT_EXCEEDED(HttpStatus.ACCEPTED, "LIMIT_EXCEEDED"),
    UNABLE_TO_RESERVE(HttpStatus.ACCEPTED, "UNABLE_TO_RESERVE"),
    DISTRIBUTED_LOCK_NOT_AVAILABLE(HttpStatus.ACCEPTED,  "DISTRIBUTED_LOCK_NOT_AVAILABLE"),

    // 429 TOO_MANY_REQUESTS
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "TOO_MANY_REQUESTS"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
