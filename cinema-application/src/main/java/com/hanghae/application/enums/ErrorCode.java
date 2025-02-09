package com.hanghae.application.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // 객체 형태로 변환 (응답받는 곳에서 코드값, 메시지 같이 보여짐)
public enum ErrorCode {
    /**
     * 	코드 = 도메인 + 상세코드
     * 	SYS : 공통
     * 	MOV : 영화
     * 	RSV : 예매
     * 	ERR : 오류
     */
    SEARCH_REQUEST_BLOCKED("SYS-001", "너무 많은 요청으로 차단되었습니다."),
    RATE_LIMIT_EXCEEDED("SYS-002", "일정 시간내 재요청이 불가합니다."),
    SEAT_NOT_AVAILABLE("RSV-001", "해당 좌석은 예매할 수 없습니다."),
    RESERVATION_LIMIT_EXCEEDED("RSV-002", "상영시간별 최대 예매 수를 초과 하였습니다."),
    SEAT_ALREADY_RESERVED("RSV-003", "이미 예매된 좌석입니다."),
    SEAT_NOT_FOUND("RSV-004", "선택할 수 없는 좌석입니다."),
    UNDEFINED_ERROR("ERR-999", "서버 내부에 오류가 발생했습니다.");

    private final String code;
    private final String message;
}
