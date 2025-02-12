package hellojpa.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RateLimitExceedException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final int customCode;

    public RateLimitExceedException(String message) {
        super(message);
        this.httpStatus = HttpStatus.TOO_MANY_REQUESTS; // 429 상태 코드
        this.customCode = 42901;
    }
}