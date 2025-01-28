package com.hanghae.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) //null값 JSON에서 제외
public record ApiResponse<T> (
    String message,
    int status,
    T data
){
    public static <T> ApiResponse<T> of(String message, int status) {
        return new ApiResponse<>(message, status, null);
    }

    public static <T> ApiResponse<T> of(String message, int status, T data) {
        if ((data == null || (data instanceof List<?> list && list.isEmpty())) && (message == null || message.isEmpty())) {
            return new ApiResponse<>("조회된 결과가 없습니다.", 200, null);
        }
        return new ApiResponse<>(message, status, data);
    }
}
