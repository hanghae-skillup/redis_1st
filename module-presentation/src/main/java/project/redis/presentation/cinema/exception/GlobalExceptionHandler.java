package project.redis.presentation.cinema.exception;


import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.redis.common.exception.DataInvalidException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, List<ErrorResponse>>> handleConstraintViolationException(
            ConstraintViolationException e, Locale locale) {
        Map<String, List<ErrorResponse>> errors = new HashMap<>();
        e.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String errorCode = constraintViolation.getMessageTemplate();
                    String message = messageSource.getMessage(errorCode, null, locale);

                    errors.computeIfAbsent(
                            constraintViolation.getPropertyPath().toString(),
                            key -> new ArrayList<>()
                    ).add(new ErrorResponse(errorCode, message));

                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }

    @ExceptionHandler(DataInvalidException.class)
    public ResponseEntity<ErrorResponse> handleDataInvalidException(
            DataInvalidException e, Locale locale) {
        String errorCode = e.getErrorCode().getMessageId();
        Object[] args = e.getArgs();
        String message = messageSource.getMessage(errorCode, args, locale);

        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
