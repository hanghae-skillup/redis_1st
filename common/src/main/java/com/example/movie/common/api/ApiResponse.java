package com.example.movie.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * ApiResponse
 * 공통 응답 response
 *
 * @param <D>
 */
@Getter
@NoArgsConstructor
public class ApiResponse<D>{
    private String code;
    private HttpStatus status;
    private String message;
    private D data;

    public ApiResponse(HttpStatus status, D data) {
        this.code = status.name();
        this.message = status.getReasonPhrase();
        this.status = status;
        this.data = data;
    }

    public static <D> ApiResponse<D> success(D data) {
        return new ApiResponse<>(HttpStatus.OK, data);
    }

    public static ApiResponse<?> exception(HttpStatus responseType) {
        return new ApiResponse<>(responseType, null);
    }
}
