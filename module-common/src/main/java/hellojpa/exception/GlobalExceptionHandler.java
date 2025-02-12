package hellojpa.exception;

import hellojpa.dto.RateLimitResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation 실패 시 발생하는 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 첫 번째 에러 메시지 가져오기
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Invalid input";
        return ResponseEntity.badRequest().body(errorMessage);
    }

    // SeatReservationException 처리
    @ExceptionHandler(SeatReservationException.class)
    public ResponseEntity<RateLimitResponseDto> handleSeatReservationException(SeatReservationException ex) {
        // 예외 메시지를 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RateLimitResponseDto(400, "SEAT_EXCEPTION", ex.getMessage(), null));
    }

    @ExceptionHandler(RateLimitExceedException.class)
    public ResponseEntity<RateLimitResponseDto> handleRateLimitExceededException(RateLimitExceedException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(RateLimitResponseDto.error());
    }
}
