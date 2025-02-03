package org.example.exception;

import org.example.baseresponse.ResponseStatus;

public class SeatException extends BaseException {
    public SeatException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}