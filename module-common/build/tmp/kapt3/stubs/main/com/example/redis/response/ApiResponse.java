package com.example.redis.response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/redis/response/ApiResponse;", "T", "", "status", "Lorg/springframework/http/HttpStatus;", "message", "", "data", "errors", "", "Lcom/example/redis/response/ErrorResponse;", "timestamp", "Ljava/time/LocalDateTime;", "(Lorg/springframework/http/HttpStatus;Ljava/lang/String;Ljava/lang/Object;Ljava/util/List;Ljava/time/LocalDateTime;)V", "module-common"})
public final class ApiResponse<T extends java.lang.Object> {
    
    public ApiResponse(@org.jetbrains.annotations.NotNull()
    org.springframework.http.HttpStatus status, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    T data, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.response.ErrorResponse> errors, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime timestamp) {
        super();
    }
}