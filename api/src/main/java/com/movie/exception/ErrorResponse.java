package com.movie.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private List<FieldError> errors;
    private String path;

    private ErrorResponse(ErrorCode errorCode, String path) {
        this.timestamp = LocalDateTime.now();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = new ArrayList<>();
        this.path = path;
    }

    private ErrorResponse(ErrorCode errorCode, String path, List<FieldError> errors) {
        this.timestamp = LocalDateTime.now();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errors = errors;
        this.path = path;
    }

    public static ErrorResponse of(ErrorCode errorCode, String path) {
        return new ErrorResponse(errorCode, path);
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult, String path) {
        return new ErrorResponse(errorCode, path, FieldError.of(bindingResult));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
} 