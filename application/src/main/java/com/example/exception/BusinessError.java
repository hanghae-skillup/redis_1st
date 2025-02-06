package com.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    // 유저관련 exception 3000
    USER_LOGIN_ERROR(HttpStatus.BAD_REQUEST, 3000, "로그인이 필요합니다."),
    USER_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST,3001, "회원 정보가 없습니다."),

    // 영화관련 exception 4000 ~ 4999
    MOVIE_SEARCH_TITLE_ERROR(HttpStatus.BAD_REQUEST,4000, "영화 제목은 225자 이하로 입력해주세요"),
    MOVIE_SEARCH_GENRE_ERROR(HttpStatus.BAD_REQUEST,4001, "유효하지않은 장르입니다"),
    MOVIE_SEARCH_MAX_FIND_ERROR(HttpStatus.TOO_MANY_REQUESTS,4002, "잠시 후 다시 시도해주세요"),

    // 예매관련 exception 5000 ~ 5999
    RESERVATION_SCREENING_SELECT_ERROR(HttpStatus.BAD_REQUEST,5000, "예매할 상영시간을 선택해주세요."),
    RESERVATION_SEAT_CONTINUOUS_ERROR(HttpStatus.BAD_REQUEST, 5001, "좌석 예매는 연속적인 좌석만 예매 가능합니다."),
    RESERVATION_SEAT_MAX_SIZE_ERROR(HttpStatus.BAD_REQUEST, 5002, "5개 이상의 좌석은 예약할 수 없습니다."),
    RESERVATION_SEAT_NOT_MATCH_ERROR(HttpStatus.BAD_REQUEST, 5003, "좌석 정보가 일치하지 않습니다."),
    RESERVATION_SEAT_TOTAL_COUNT_ERROR(HttpStatus.BAD_REQUEST,5004, "하나의 상영시간에 5좌석이상 예매할 수 없습니다"),
    RESERVATION_EXIST_ERROR(HttpStatus.BAD_REQUEST,5005, "이미 예매된 좌석입니다."),
    RESERVATION_SEAT_SELECT_ERROR(HttpStatus.BAD_REQUEST, 5006, "예매할 좌석을 선택해주세요"),
    RESERVATION_RATE_LIMIT_ERROR(HttpStatus.TOO_MANY_REQUESTS, 5007, "같은 상영시간은 5분후 예매할 수 있습니다."),

    // 상영관련 exception 6000 ~ 6999
    SCREENING_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, 6000, "상영 정보가 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    BusinessError(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public BusinessException exception() {
        return new BusinessException(httpStatus, code, message);
    }
}
