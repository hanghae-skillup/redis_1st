package com.example.movie.message

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MessagePublisher(
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    @Async
    fun sendReservationCompleteMessage(message: ReservationCompleteMessage) {
        Thread.sleep(500)
        logger.info("Message sent: $message")
    }
}