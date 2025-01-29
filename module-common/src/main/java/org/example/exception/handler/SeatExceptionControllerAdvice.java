package org.example.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.baseresponse.error.BaseErrorResponse;
import org.example.exception.SeatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SeatExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SeatException.class})
    public BaseErrorResponse handle_BaseException(SeatException e) {
        log.error("[handle_BadRequest]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

}
