package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/example/redis/movie/in/dto/ScreeningScheduleResponseDto;", "", "startTime", "Ljava/time/LocalDateTime;", "endTime", "(Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;)V", "getEndTime", "()Ljava/time/LocalDateTime;", "getStartTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "module-infrastructure"})
public final class ScreeningScheduleResponseDto {
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime startTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime endTime = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.in.dto.ScreeningScheduleResponseDto.Companion Companion = null;
    
    public ScreeningScheduleResponseDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "start_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startTime, @com.fasterxml.jackson.annotation.JsonProperty(value = "end_time")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endTime) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getStartTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getEndTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.in.dto.ScreeningScheduleResponseDto copy(@com.fasterxml.jackson.annotation.JsonProperty(value = "start_time")
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/redis/movie/in/dto/ScreeningScheduleResponseDto$Companion;", "", "()V", "toDto", "Lcom/example/redis/movie/in/dto/ScreeningScheduleResponseDto;", "screeningSchedule", "Lcom/example/redis/movie/ScreeningSchedule;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.in.dto.ScreeningScheduleResponseDto toDto(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.ScreeningSchedule screeningSchedule) {
            return null;
        }
    }
}