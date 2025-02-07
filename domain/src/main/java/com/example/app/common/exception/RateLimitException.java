package com.example.app.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RateLimitException extends RuntimeException {

    private final HttpStatus httpStatus;

    public RateLimitException() {
        super("요청이 너무 많아요");
        this.httpStatus = HttpStatus.TOO_MANY_REQUESTS;
    }
}
