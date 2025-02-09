package org.example.exception;

import lombok.Getter;
import org.example.baseresponse.ResponseStatus;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseStatus exceptionStatus;

    public BaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}