package com.example.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final static int DEFAULT_OK_CODE = 2000;
    private final static int DEFAULT_CREATE_CODE = 2001;

    private HttpStatus status;
    private int code;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(HttpStatus.OK, DEFAULT_OK_CODE, message, data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(HttpStatus.CREATED, DEFAULT_CREATE_CODE, message, data);
    }

    public static ApiResponse<Void> businessException(HttpStatus httpstatus, int code, String message) {
        return new ApiResponse<>(httpstatus != null ? httpstatus : HttpStatus.BAD_REQUEST, code, message, null);
    }
}
