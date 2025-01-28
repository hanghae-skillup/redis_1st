package com.example.exception;

import com.example.response.BaseErrorResponse;
import com.example.response.code.BaseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BaseException.class,NoHandlerFoundException.class, TypeMismatchException.class})
    public BaseErrorResponse handle_BadRequest(Exception exception) {
        log.info("[BaseExceptionControllerAdvice: handle_BadRequest 호출]", exception);
        return new BaseErrorResponse(BaseCode.URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseErrorResponse handle_HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("[BaseExceptionControllerAdvice: handle_HttpRequestMethodNotSupportedException 호출]", e);
        return new BaseErrorResponse(BaseCode.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseErrorResponse handle_IllegalArgumentException(IllegalArgumentException e) {
        log.info("[BaseExceptionControllerAdvice: handle_IllegalArgumentException 호출]", e);
        return new BaseErrorResponse(BaseCode.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public BaseErrorResponse handle_IllegalStatusException(IllegalStateException e) {
        log.info("[BaseExceptionControllerAdvice: handle_IllegalStatusException 호출]", e);
        return new BaseErrorResponse(BaseCode.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public BaseErrorResponse handle_IOException(IOException e) {
        log.info("[BaseExceptionControllerAdvice: handle_IOException 호출]", e);
        return new BaseErrorResponse(BaseCode.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("[GlobalExceptionHandler] MissingServletRequestParameterException", e);
        return new BaseErrorResponse(BaseCode.NO_REQUEST_PARAMETER);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseErrorResponse handle_RuntimeException(Exception e) {
        log.error("[BaseExceptionControllerAdvice: handle_RuntimeException 호출]", e);
        return new BaseErrorResponse(BaseCode.SERVER_ERROR);
    }
}
