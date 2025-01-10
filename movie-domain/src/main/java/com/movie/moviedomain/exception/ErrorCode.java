package com.movie.moviedomain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    CONTENT_NOT_FOUND(HttpStatus.ACCEPTED, "CONTENT_NOT_FOUND"),


    ;

    private final HttpStatus httpStatus;
    private final String message;

}
