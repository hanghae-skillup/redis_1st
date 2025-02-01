package com.example.application.exception;

import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.example.domain.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode().name(),
                ex.getCustomMessage(),
                ex.getErrorCode().getStatusCode()
        );
        return ResponseEntity.status(ex.getErrorCode().getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                500
        );
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockFailure(ObjectOptimisticLockingFailureException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.OPTIMISTIC_LOCK_CONFLICT.name(),
                ErrorCode.OPTIMISTIC_LOCK_CONFLICT.getMessage(),
                ErrorCode.OPTIMISTIC_LOCK_CONFLICT.getStatusCode()
        );
        return ResponseEntity.status(ErrorCode.OPTIMISTIC_LOCK_CONFLICT.getStatusCode()).body(errorResponse);
    }

}
