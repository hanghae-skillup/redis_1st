package com.example.movie.domain.common

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultTimeHandler : TimeHandler {
    override fun getCurrentTime(): LocalDateTime {
        return LocalDateTime.now()
    }
}