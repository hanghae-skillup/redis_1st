package exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

	private final HttpStatus httpStatus;
	private final int code;

	public BusinessException(HttpStatus httpStatus, int code, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
	}
}
