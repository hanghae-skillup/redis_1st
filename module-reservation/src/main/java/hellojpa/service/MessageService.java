package hellojpa.service;

import hellojpa.dto.ReservationCompletedMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    @EventListener
    public void handleReservationCompletedEvent(ReservationCompletedMessageDto event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        sendMessageAsync(event);
    }

    @Async
    void sendMessageAsync(ReservationCompletedMessageDto event) {
        try {
            Thread.sleep(500); // 비지니스 로직 처리 + 메시지 발송
            log.info("[MessageService] UserId: {} - {}", event.getUserId(), event.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("메시지 발송 중 오류가 발생했습니다.");
        }
    }
}