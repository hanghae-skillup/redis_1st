package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieTheaterResponseDto;", "", "theaterName", "", "screeningSchedules", "", "Lcom/example/redis/movie/in/dto/ScreeningScheduleResponseDto;", "(Ljava/lang/String;Ljava/util/List;)V", "getScreeningSchedules", "()Ljava/util/List;", "getTheaterName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "module-infrastructure"})
public final class MovieTheaterResponseDto {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String theaterName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.in.dto.ScreeningScheduleResponseDto> screeningSchedules = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.in.dto.MovieTheaterResponseDto.Companion Companion = null;
    
    public MovieTheaterResponseDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "theater_name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String theaterName, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_schedules")
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.in.dto.ScreeningScheduleResponseDto> screeningSchedules) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTheaterName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.in.dto.ScreeningScheduleResponseDto> getScreeningSchedules() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.in.dto.ScreeningScheduleResponseDto> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.in.dto.MovieTheaterResponseDto copy(@com.fasterxml.jackson.annotation.JsonProperty(value = "theater_name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String theaterName, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_schedules")
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.in.dto.ScreeningScheduleResponseDto> screeningSchedules) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieTheaterResponseDto$Companion;", "", "()V", "toDto", "Lcom/example/redis/movie/in/dto/MovieTheaterResponseDto;", "movieTheater", "Lcom/example/redis/movie/MovieTheater;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.in.dto.MovieTheaterResponseDto toDto(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.MovieTheater movieTheater) {
            return null;
        }
    }
}