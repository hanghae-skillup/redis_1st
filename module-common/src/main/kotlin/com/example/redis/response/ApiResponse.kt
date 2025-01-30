package com.example.redis.response

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ApiResponse<T>(
    status: HttpStatus,
    message: String,
    data: T? = null,
    errors: MutableList<ErrorResponse> = mutableListOf(),
    timestamp: LocalDateTime = LocalDateTime.now(),
) {
}