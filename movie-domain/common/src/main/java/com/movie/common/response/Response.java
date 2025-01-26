package com.movie.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static <T> Response<T> success() {
        return new Response<T>("SUCCESS", null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>("SUCCESS", result);
    }

    public static <T> Response<T> error(T resultCode) {
        return new Response<T>("ERROR", resultCode);
    }

    public static <T> Response<T> error(String resultCode, T message) {
        return new Response<T>(resultCode, message);
    }

}
