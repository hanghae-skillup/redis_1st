package com.movie.common.exception;

import org.springframework.http.HttpStatus;

public class RateLimitExceededException extends BusinessException {
    private static final String CODE = "RATE_LIMIT_EXCEEDED";
    private static final HttpStatus STATUS = HttpStatus.TOO_MANY_REQUESTS;

    public RateLimitExceededException(String message) {
        super(message, STATUS, CODE);
    }
} 