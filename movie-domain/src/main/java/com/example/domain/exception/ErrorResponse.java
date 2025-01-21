package com.example.domain.exception;

public record ErrorResponse(String errorCode, String message, int statusCode) {
}
