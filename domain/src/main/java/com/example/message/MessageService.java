package com.example.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    public void send() throws InterruptedException {
        Thread.sleep(500);
        log.info("예매가 성공적으로 되었습니다.");
    }

}
