package com.movie.api.response;

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

    public static ApiResponse<?> error(String code, String message) {
        return new ApiResponse<>(false, null, new Error(code, message));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Error {
        private String code;
        private String message;

        private Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
} 