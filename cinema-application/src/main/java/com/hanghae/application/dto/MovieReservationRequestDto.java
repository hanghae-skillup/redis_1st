package com.hanghae.application.dto;

import com.hanghae.domain.model.enums.ScreenSeat;

public record MovieReservationRequestDto (
        Long scheduleId, // 상영시간표 ID
        Long memberId, //회원 아이디
        ScreenSeat screenSeat, // 좌석명
        int seatCount //예약 좌석 갯수
){
    // 생성자를 통해 기본값 설정
    public MovieReservationRequestDto(Long scheduleId, Long memberId, ScreenSeat screenSeat, int seatCount) {
        this.scheduleId = scheduleId;
        this.memberId = (memberId != null) ? memberId : 1; // 로그인 기능 부재로 회원 기본값 적용
        this.screenSeat = screenSeat;
        this.seatCount = seatCount;
    }
}
