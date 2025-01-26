package com.example.app.booking.service;

import com.example.app.booking.usecase.SendMessageUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendMessageService implements SendMessageUseCase {

    public void sendMessage(String message) throws InterruptedException {
        log.info(">>>>> Sending message: {}", message);
        Thread.sleep(500);
        log.info(">>>>> Sent");
    }
}
