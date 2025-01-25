package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB7\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lcom/example/redis/movie/in/dto/ScreeningResponseDto;", "", "theaterId", "", "theaterName", "", "screeningId", "startTime", "Ljava/time/LocalDateTime;", "endTime", "(JLjava/lang/String;JLjava/time/LocalDateTime;Ljava/time/LocalDateTime;)V", "getEndTime", "()Ljava/time/LocalDateTime;", "getScreeningId", "()J", "getStartTime", "getTheaterId", "getTheaterName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "module-infrastructure"})
public final class ScreeningResponseDto {
    private final long theaterId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String theaterName = null;
    private final long screeningId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime startTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime endTime = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.in.dto.ScreeningResponseDto.Companion Companion = null;
    
    public ScreeningResponseDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "theater_id")
    long theaterId, @com.fasterxml.jackson.annotation.JsonProperty(value = "theater_name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String theaterName, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_id")
    long screeningId, @com.fasterxml.jackson.annotation.JsonProperty(value = "start_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startTime, @com.fasterxml.jackson.annotation.JsonProperty(value = "end_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endTime) {
        super();
    }
    
    public final long getTheaterId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTheaterName() {
        return null;
    }
    
    public final long getScreeningId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getStartTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getEndTime() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.in.dto.ScreeningResponseDto copy(@com.fasterxml.jackson.annotation.JsonProperty(value = "theater_id")
    long theaterId, @com.fasterxml.jackson.annotation.JsonProperty(value = "theater_name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String theaterName, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_id")
    long screeningId, @com.fasterxml.jackson.annotation.JsonProperty(value = "start_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startTime, @com.fasterxml.jackson.annotation.JsonProperty(value = "end_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endTime) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/redis/movie/in/dto/ScreeningResponseDto$Companion;", "", "()V", "toDto", "Lcom/example/redis/movie/in/dto/ScreeningResponseDto;", "screening", "Lcom/example/redis/movie/Screening;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.in.dto.ScreeningResponseDto toDto(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.Screening screening) {
            return null;
        }
    }
}