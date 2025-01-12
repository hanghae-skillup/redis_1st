package com.bmsnc.common;


import com.bmsnc.common.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( {EntityNotFoundException.class} )
    public Result handleBadRequestExceptions(Exception e){
        return Result.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .data(e.getMessage())
                .build();
    }

}
