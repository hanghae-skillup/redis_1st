package com.example.application.message.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationCompletedEvent {
    private final Long reservationId;
    private final String memberEmail; // 유저 이메일
    private final String screeningInfo; // 메시지 발송 내용
}
