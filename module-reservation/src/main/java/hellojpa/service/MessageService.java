package hellojpa.service;

import hellojpa.dto.ReservationCompletedMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    @EventListener
    public void handleReservationCompletedEvent(ReservationCompletedMessageDto event) {
        try {
            Thread.sleep(500); // 비지니스 로직 처리 + 메시지 발송
            System.out.println("[MessageService] UserId: " + event.getUserId() + " - " + event.getMessage());
            log.info("[MessageService] UserId: {} - {}", event.getUserId(), event.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("메시지 발송 중 오류가 발생했습니다.");
        }
    }
}