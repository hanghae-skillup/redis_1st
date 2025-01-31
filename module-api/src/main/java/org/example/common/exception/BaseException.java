package org.example.common.exception;

import lombok.Getter;
import org.example.common.response.ResponseStatus;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseStatus exceptionStatus;

    public BaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}