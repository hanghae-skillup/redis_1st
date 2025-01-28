package org.haas.adapter.exception;

import org.haas.application.exception.InvalidScreeningDateException;
import org.haas.core.domain.exception.MovieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieException.class)
    ResponseEntity<String> handleMovieException(MovieException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidScreeningDateException.class)
    public ResponseEntity<String> handleInvalidScreeningDateException(InvalidScreeningDateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
