package com.hanghae.adapter.web.exception;

import com.hanghae.application.dto.ApiResponse;
import com.hanghae.application.enums.ErrorCode;
import com.hanghae.application.exception.CustomRequestException;
import com.hanghae.application.exception.CustomServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred: {}", e.getMessage(), e);  // 로그 추가
        ApiResponse<Void> apiResponse = ApiResponse.of(false, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException occurred: {}", e.getMessage(), e);  // 로그 추가
        ApiResponse<Void> apiResponse = ApiResponse.of(false, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException occurred: {}", e.getMessage(), e);  // 로그 추가
        ApiResponse<Void> apiResponse = ApiResponse.of(false, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(CustomRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomRequestException(CustomRequestException e) {
        log.error("CustomRequestException occurred: {}", e.getMessage(), e);  // 로그 추가
        ApiResponse<Void> apiResponse = ApiResponse.of(e.getMessage(), e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(CustomServerException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomServerException(CustomServerException e) {
        log.error("CustomServerException occurred: {}", e.getMessage(), e);  // 로그 추가
        ApiResponse<Void> apiResponse = ApiResponse.of(e.getMessage(), e.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    /**
     * 모든 예외를 처리하는 범용 핸들러 (위의 예외에 해당하지 않는 경우)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception e) {
        log.error("Unhandled exception occurred: {}", e.getMessage(), e);
        ApiResponse<Void> apiResponse = ApiResponse.of("서버 내부에 오류가 발생했습니다.", ErrorCode.UNDEFINED_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
