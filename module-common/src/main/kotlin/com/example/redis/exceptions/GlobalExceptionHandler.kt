package com.example.redis.exceptions

import com.example.redis.response.ApiResponse
import com.example.redis.response.ErrorResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> {
        // 유효성 검증 실패 항목들을 순회하며 메시지 생성
        val errors = ex.bindingResult.allErrors.map {
            ErrorResponse(
                field = (it as FieldError).field,
                value = "",
                reason = it.defaultMessage ?: "Invalid value"
            )
        }.toMutableList()

        val response = ApiResponse<Unit>(
                status = HttpStatus.BAD_REQUEST,
                message = "",
                errors = errors,
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}