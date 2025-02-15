package exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BusinessError {
	// 유저 관련 Exception
	USER_NOT_FOUNT(HttpStatus.BAD_REQUEST, 3000, "존재하지 않는 id 잆니다."),

	// 상영정보 관련 Exception
	SHOWING_NOT_FOUND(HttpStatus.NOT_FOUND, 4000, "존재하지 않는 상영정보입니다."),
	SHOWING_TOO_MANY_REQUEST(HttpStatus.TOO_MANY_REQUESTS, 4001, "요청을 너무 많이 보냈습니다."),

	// 예매 관련
	RESERVATION_INVALID_AGE_FOR_MOVIE(HttpStatus.BAD_REQUEST, 5001, "상영영화의 관람등급과 고객님의 나이가 맞지 않습니다."),
	RESERVATION_INVALID_SEAT_CONDITION(HttpStatus.BAD_REQUEST, 5002, "여러 티켓 구매의 경우 연속된 좌석만 구매하실 수 있습니다."),
	RESERVATION_INVALID_TICKET(HttpStatus.BAD_REQUEST, 5003, "부적절한 예매입니다. 재시도 바랍니다."),
	RESERVATION_NO_RESERVATION_INFO(HttpStatus.BAD_REQUEST, 5004, "예매정보가 없습니다."),
	RESERVATION_NOT_ON_SALE_TICKET(HttpStatus.BAD_REQUEST, 5005, "이미 예매중인 티켓입니다."),
	RESERVATION_TOO_MANY_RESERVATION(HttpStatus.BAD_REQUEST, 5006, "한 시간표에 1인 5매의 티켓만 구매 가능합니다."),
	RESERVATION_TOO_MANY_RESERVATION_REQUEST(HttpStatus.TOO_MANY_REQUESTS, 5007, "5분 뒤 다시 시도해주세요"),
	RESERVATION_TRY_LOCK_FAILED(HttpStatus.CONFLICT, 5008, "현재 접속자가 많습니다. 잠시후 재시도 바랍니다.");

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
