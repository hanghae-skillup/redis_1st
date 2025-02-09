package com.hanghae.application.exception;

import com.hanghae.application.enums.ErrorCode;
import lombok.Getter;

/**
 * 요청 오류 Exception
 * 400 응답위해 Exception 구분
 */
@Getter
public class CustomRequestException extends RuntimeException {
    private final String message;
    private final ErrorCode errorCode;

    public CustomRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = null;
    }

    public CustomRequestException(String message, ErrorCode errorCode) {
        super(message != null ? message : errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorMessage() {
        return message != null ? message : errorCode.getMessage();
    }
}
