package com.hanghae.application.exception;

import com.hanghae.application.enums.ErrorCode;
import lombok.Getter;

/**
 * 서버 내부 오류 Exception
 * 500 응답위해 Exception 구분
 */
@Getter
public class CustomServerException extends RuntimeException {
    private final String message;
    private final ErrorCode errorCode;

    public CustomServerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = null;
    }

    public CustomServerException(String message, ErrorCode errorCode) {
        super(message != null ? message : errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorMessage() {
        return message != null ? message : errorCode.getMessage();
    }
}
