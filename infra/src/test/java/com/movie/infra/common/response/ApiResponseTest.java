package com.movie.infra.common.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    @DisplayName("성공 응답 생성")
    void createSuccessResponse() {
        // given
        String data = "test data";

        // when
        ApiResponse<String> response = ApiResponse.success(data);

        // then
        assertThat(response.getCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getMessage()).isEqualTo("Success");
        assertThat(response.getData()).isEqualTo(data);
    }

    @Test
    @DisplayName("에러 응답 생성 - HttpStatus 사용")
    void createErrorResponseWithHttpStatus() {
        // when
        ApiResponse<?> response = ApiResponse.error(HttpStatus.BAD_REQUEST, "Invalid input");

        // then
        assertThat(response.getCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getMessage()).isEqualTo("Invalid input");
        assertThat(response.getData()).isNull();
    }

    @Test
    @DisplayName("에러 응답 생성 - 코드와 메시지 직접 지정")
    void createErrorResponseWithCodeAndMessage() {
        // when
        ApiResponse<?> response = ApiResponse.error(429, "Too many requests");

        // then
        assertThat(response.getCode()).isEqualTo(429);
        assertThat(response.getMessage()).isEqualTo("Too many requests");
        assertThat(response.getData()).isNull();
    }
} 