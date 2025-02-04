package com.movie.common.response;

import com.movie.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private Error error;

    private ApiResponse(boolean success, T data, Error error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<Page<T>> success(Page<T> page) {
        return new ApiResponse<>(true, page, null);
    }

    public static ApiResponse<?> error(ErrorCode errorCode) {
        return new ApiResponse<>(false, null, new Error(errorCode));
    }

    public static ApiResponse<?> error(ErrorCode errorCode, String message) {
        return new ApiResponse<>(false, null, new Error(errorCode, message));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Error {
        private String code;
        private String message;

        private Error(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.message = errorCode.getMessage();
        }

        private Error(ErrorCode errorCode, String message) {
            this.code = errorCode.getCode();
            this.message = message;
        }
    }
} 