package com.example.message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageService {

    public void send(Long userId, Long reservationId) throws InterruptedException {
        Thread.sleep(500);
        log.info("예매가 성공적으로 되었습니다. (유저번호: " + userId + ", 예매번호: " + reservationId +")");
    }

}
