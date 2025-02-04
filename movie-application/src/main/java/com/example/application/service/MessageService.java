package com.example.application.service;

import com.example.application.port.in.MessageServicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService implements MessageServicePort {

    @Override
    public void send(String message) {
        try {
            log.info("[MessageService] 전송 시작: {}", message);
            Thread.sleep(500); // 메시지 전송에 걸리는 시간 가정
            log.info("[MessageService] 전송 완료: {}", message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 현재 스레드가 인터럽트 상태임을 유지하도록 설정
            log.error("[MessageService] 전송 실패!", e);
        }
    }

}
