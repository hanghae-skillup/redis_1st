package org.example.common.exception;

import org.example.common.response.ResponseStatus;

public class SeatException extends BaseException {
    public SeatException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}