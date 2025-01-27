package com.movie.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("handleBusinessException", e);
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode, request.getRequestURI());
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("handleMethodArgumentNotValidException", e);
        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult(), request.getRequestURI());
        return new ResponseEntity<>(response, ErrorCode.INVALID_INPUT_VALUE.getStatus());
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e, HttpServletRequest request) {
        log.error("handleBindException", e);
        ErrorResponse response = ErrorResponse.of(
                ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult(), request.getRequestURI());
        return new ResponseEntity<>(response, ErrorCode.INVALID_INPUT_VALUE.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("handleException", e);
        ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, request.getRequestURI());
        return new ResponseEntity<>(response, ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
    }
} 