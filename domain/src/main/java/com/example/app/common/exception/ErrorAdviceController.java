package com.example.app.common.exception;

import com.example.app.common.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ErrorAdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.name(), message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorMessage> handleAPIException(APIException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getHttpStatus().name(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(LockException.class)
    public ResponseEntity<ErrorMessage> handleLockException(LockException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getHttpStatus().name(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
