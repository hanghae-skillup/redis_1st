package com.example.movie.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API 응답 객체")
public class ApiResponse<D>{
    @Schema(description = "응답 코드")
    private String code;
    @Schema(description = "응답 상태")
    private HttpStatus status;
    @Schema(description = "응답 메시지")
    private String message;
    @Schema(description = "응답 데이터")
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
