package com.hh.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CinemaException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public CinemaException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s, %s", errorCode.getMessage(), message);
    }

}
