package com.example.movie.domain.common

import java.time.LocalDateTime

interface TimeHandler {
    fun getCurrentTime(): LocalDateTime
}