package com.movie.domain.movie.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReservationMessageEventListener {

    /**
        messageSender 를 slack 이나 kakao 채널을 이용한다면 어떻게 DI 를 적용할지 고려해야 함
     */
    private final ReservationMessageSender messageSender;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessage(ReservationMessage.Send send) throws InterruptedException {
        Thread.sleep(500);
        log.info(send.message());
        messageSender.send(send);
    }

}
