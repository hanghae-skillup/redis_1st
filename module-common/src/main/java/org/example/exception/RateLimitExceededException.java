package org.example.exception;

import org.example.baseresponse.ResponseStatus;

public class RateLimitExceededException extends BaseException {
    public RateLimitExceededException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
    }
}
