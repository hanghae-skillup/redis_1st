package com.example.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogMessageService implements MessageService {
    @Override
    public void send() {
        log.info("FCM 메시지 발송");
    }
}
