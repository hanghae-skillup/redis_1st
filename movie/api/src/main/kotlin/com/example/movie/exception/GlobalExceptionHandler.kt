package com.example.movie.exception

import com.example.movie.domain.reservation.exception.ReservationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage ?: "Invalid value"
        }
        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler(ReservationException::class)
    fun handleReservationException(e: ReservationException): ResponseEntity<Map<String, String>> {
        return ResponseEntity.badRequest()
            .body(mapOf("message" to e.message.toString()))
    }
}