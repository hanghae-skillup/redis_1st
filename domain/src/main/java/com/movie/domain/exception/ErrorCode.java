package com.movie.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    SCHEDULE_NOT_FOUND("상영 일정을 찾을 수 없습니다."),
    SEAT_NOT_FOUND("좌석을 찾을 수 없습니다."),
    SEAT_ALREADY_RESERVED("이미 예약된 좌석입니다."),
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다."),
    INVALID_RESERVATION_STATUS("잘못된 예약 상태입니다.");

    private final String message;
} 