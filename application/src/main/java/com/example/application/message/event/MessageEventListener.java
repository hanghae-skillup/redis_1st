package com.example.application.message.event;

import com.example.application.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageEventListener {

    private final MessageService messageService;

    @EventListener
    public void handleReservationCompletedEvent(ReservationCompletedEvent event) {
        // 메시지 발송 처리
        String message = String.format(
                "예약이 완료되었습니다.\n예약 번호: %d\n상영 정보: %s\n고객 이메일: %s",
                event.getReservationId(),
                event.getScreeningInfo(),
                event.getMemberEmail()
        );

        messageService.send(message);
    }
}
