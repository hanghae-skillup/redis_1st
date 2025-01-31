package org.example.common.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseResponseStatus implements ResponseStatus {
    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    /**
     * 5000: 좌석 정보 오류
     */
    UNAVAILABLE_SEAT_ERROR(5001, HttpStatus.BAD_REQUEST.value(), "예약할 수 없는 좌석입니다."),
    CONCURRENT_RESERVATION_ERROR(5002, HttpStatus.BAD_REQUEST.value(), "다른 사용자가 이미 예약을 진행 중입니다. 다시 시도해 주세요."),
    MAX_SEATS_EXCEEDED_ERROR(5003, HttpStatus.BAD_REQUEST.value(), "최대 예약 가능한 좌석을 초과했습니다."),
    SEAT_ROW_DISCONTINUITY_ERROR(5004, HttpStatus.BAD_REQUEST.value(), "연속된 좌석만 예약할 수 있습니다. 행이 다릅니다."),
    SEAT_COLUMN_DISCONTINUITY_ERROR(5005, HttpStatus.BAD_REQUEST.value(), "연속된 좌석만 예약할 수 있습니다. 열이 연속되지 않았습니다."),
    ALREADY_RESERVED_SEAT_ERROR(5006, HttpStatus.BAD_REQUEST.value(), "이미 예약된 좌석입니다.");


    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
