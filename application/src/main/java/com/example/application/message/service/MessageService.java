package com.example.application.message.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public void send(String message) {
        try {
            Thread.sleep(500); // 메시지 발송 모사
            System.out.println("메시지가 발송되었습니다: " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
