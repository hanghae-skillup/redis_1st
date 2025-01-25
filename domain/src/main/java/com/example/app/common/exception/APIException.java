package com.example.app.common.exception;

import com.example.app.booking.exception.BookingErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {

    private final HttpStatus httpStatus;

    public APIException(BookingErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.httpStatus = errorMessage.getHttpStatus();
    }
}
