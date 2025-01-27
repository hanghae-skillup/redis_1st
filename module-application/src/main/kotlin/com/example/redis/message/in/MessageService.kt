package com.example.redis.message.`in`

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Service
class MessageService: MessageUseCase {

    override fun send() {
        Thread.sleep(500)
        println("예약 발송 성공")
    }
}