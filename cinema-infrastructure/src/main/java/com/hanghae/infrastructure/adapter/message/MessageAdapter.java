package com.hanghae.infrastructure.adapter.message;

import com.hanghae.application.port.out.message.MessagePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageAdapter implements MessagePort {

    @Override
    @Async("messageTaskExecutor")
    public void sendMessage(String message) {
        try {
            Thread.sleep(500); //메시지 발송을 위한 비지니스 로직 처리와 메시지 발송을 포함해서 500ms 정도 걸린다고 가정
            log.info("메시지 전송이 완료 되었습니다.\n내용: " + message);
        } catch (InterruptedException e) {
            throw new RuntimeException("메시지 전송시 문제가 발생 하였습니다.");
        }
    }
}
