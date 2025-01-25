package com.hanghae.domain.model;

import com.hanghae.domain.model.enums.ScreenSeat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TicketReservation {
    //일관된 상태 유지를 위해 @AllArgsConstructor 만 사용
    private final Long ticketId;
    private final ScreenSeat screenSeat; // 상영관 좌석
    private final ScreeningSchedule screeningSchedule; // 상영시간표
    private final Member member; // 회원
    private final Long createdBy; // 작성자 ID
    private final LocalDateTime createdAt; // 작성일
    private final Long updatedBy; // 수정자 ID
    private final LocalDateTime updatedAt; //수정일

}
