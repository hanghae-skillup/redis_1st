package com.example.app.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LockException extends RuntimeException {

    private final HttpStatus httpStatus;

    public LockException() {
        super("리소스 충돌이 있어요");
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
