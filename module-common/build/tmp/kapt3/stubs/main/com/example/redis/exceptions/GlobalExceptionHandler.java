package com.example.redis.exceptions;

@org.springframework.web.bind.annotation.RestControllerAdvice()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0017J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u000bH\u0017\u00a8\u0006\f"}, d2 = {"Lcom/example/redis/exceptions/GlobalExceptionHandler;", "", "()V", "handleIllegalStateException", "Lorg/springframework/http/ResponseEntity;", "Lcom/example/redis/response/ApiResponse;", "", "ex", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "handleValidationException", "Lorg/springframework/web/bind/MethodArgumentNotValidException;", "module-common"})
public class GlobalExceptionHandler {
    
    public GlobalExceptionHandler() {
        super();
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {java.lang.IllegalStateException.class})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.example.redis.response.ApiResponse<kotlin.Unit>> handleIllegalStateException(@org.jetbrains.annotations.NotNull()
    java.lang.IllegalStateException ex) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {org.springframework.web.bind.MethodArgumentNotValidException.class})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.example.redis.response.ApiResponse<kotlin.Unit>> handleValidationException(@org.jetbrains.annotations.NotNull()
    org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return null;
    }
}