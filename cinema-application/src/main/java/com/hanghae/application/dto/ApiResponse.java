package com.hanghae.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.application.enums.ErrorCode;
import com.hanghae.application.enums.HttpStatusCode;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) //null값 JSON에서 제외
public record ApiResponse<T> (
    boolean success, // 성공여부
    String message, // 응답메시지
    ErrorCode errorCode, // 오류 발생시 오류 코드 및 메시지
    T data // 응답 데이터
){
    // 메시지만 응답 (데이터X, 오류코드 X)
    public static <T> ApiResponse<T> of(boolean success, String message) {
        return new ApiResponse<>(success, message, null, null);
    }

    // 오류시 응딥 (데이터 X)
    public static <T> ApiResponse<T> of(String message, ErrorCode errorCode) {
        return new ApiResponse<>(false, message, errorCode, null);
    }

    // 데이터 포함된 응답 (오류코드 X)
    public static <T> ApiResponse<T> of(boolean success, String message, T data) {
        if ((data == null || (data instanceof List<?> list && list.isEmpty())) && (message == null || message.isEmpty())) {
            return new ApiResponse<>(success, "조회된 결과가 없습니다.", null, null);
        }
        return new ApiResponse<>(success, message, null, data);
    }
}
