package com.movie.api.exception;

import com.movie.common.exception.BusinessException;
import com.movie.common.exception.EntityNotFoundException;
import com.movie.common.exception.ErrorCode;
import com.movie.common.exception.RateLimitExceededException;
import com.movie.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateLimitExceededException.class)
    protected ResponseEntity<ApiResponse<?>> handleRateLimitExceededException(RateLimitExceededException e) {
        log.error("RateLimitExceededException", e);
        return ResponseEntity
                .status(ErrorCode.IP_RATE_LIMIT_EXCEEDED.getHttpStatus())
                .body(ApiResponse.error(ErrorCode.IP_RATE_LIMIT_EXCEEDED, e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException", e);
        return ResponseEntity
                .status(ErrorCode.ENTITY_NOT_FOUND.getHttpStatus())
                .body(ApiResponse.error(ErrorCode.ENTITY_NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ApiResponse.error(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    protected ResponseEntity<ApiResponse<?>> handleBindException(BindException e) {
        log.error("BindException", e);
        return ResponseEntity
                .status(ErrorCode.INVALID_INPUT_VALUE.getHttpStatus())
                .body(ApiResponse.error(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception", e);
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR));
    }
} 