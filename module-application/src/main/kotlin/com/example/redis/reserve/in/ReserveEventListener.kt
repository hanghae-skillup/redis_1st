package com.example.redis.reserve.`in`

import com.example.redis.message.`in`.MessageUseCase
import com.example.redis.movie.Reservation
import com.example.redis.movie.event.ReserveEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ReserveEventListener(
    private val messageService: MessageUseCase
) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleReserveEvent(event: ReserveEvent) {
        this.messageService.send()
    }
}