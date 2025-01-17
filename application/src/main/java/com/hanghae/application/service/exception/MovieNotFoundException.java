package com.hanghae.application.service.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Integer id) {
        super("영화를 찾을 수 없습니다. ID: " + id);
    }
}