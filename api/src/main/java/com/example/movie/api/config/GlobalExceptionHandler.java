package com.example.movie.api.config;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import com.example.movie.common.api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        HttpMediaTypeNotSupportedException.class,
        HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<ApiResponse<?>> unSupportedMediaTypeException() {
        HttpStatus responseType = UNSUPPORTED_MEDIA_TYPE;

        return ResponseEntity
            .status(responseType)
            .body(ApiResponse.exception(responseType));
    }

    /**
     * 잘못된 API 요청 Exception
     */
    @ExceptionHandler({
        ConstraintViolationException.class,
        MethodArgumentNotValidException.class,
        MethodArgumentTypeMismatchException.class,
        HttpMessageNotReadableException.class,
        MissingServletRequestParameterException.class,
    })
    public ResponseEntity<ApiResponse<?>> badRequestException(Exception e) {
        log.error(e.getMessage(), e);

        HttpStatus responseType = BAD_REQUEST;

        return ResponseEntity
            .status(responseType)
            .body(ApiResponse.exception(responseType));
    }

    /**
     * 예기치 않은 서버 예외
     */
    @ExceptionHandler({
        Exception.class,
        IllegalStateException.class
    })
    public ResponseEntity<ApiResponse<?>> unknownException(Exception e) {
        log.error(e.getMessage(), e);
        HttpStatus responseType = INTERNAL_SERVER_ERROR;

        return ResponseEntity
            .status(responseType)
            .body(ApiResponse.exception(responseType));
    }
}
