package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieReserveRequestDto;", "", "movieId", "", "userId", "screeningId", "seats", "", "Lcom/example/redis/movie/in/dto/SeatRequestDto;", "(JJJLjava/util/List;)V", "getMovieId", "()J", "getScreeningId", "getSeats", "()Ljava/util/List;", "getUserId", "module-infrastructure"})
public final class MovieReserveRequestDto {
    private final long movieId = 0L;
    private final long userId = 0L;
    private final long screeningId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.in.dto.SeatRequestDto> seats = null;
    
    public MovieReserveRequestDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "movie_id")
    @jakarta.validation.constraints.NotNull()
    long movieId, @com.fasterxml.jackson.annotation.JsonProperty(value = "user_id")
    @jakarta.validation.constraints.NotNull()
    long userId, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_id")
    @jakarta.validation.constraints.NotNull()
    long screeningId, @com.fasterxml.jackson.annotation.JsonProperty(value = "seats")
    @jakarta.validation.constraints.Size(message = "The seat must have one to five.", min = 1, max = 5)
    @jakarta.validation.Valid()
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.in.dto.SeatRequestDto> seats) {
        super();
    }
    
    public final long getMovieId() {
        return 0L;
    }
    
    public final long getUserId() {
        return 0L;
    }
    
    public final long getScreeningId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.in.dto.SeatRequestDto> getSeats() {
        return null;
    }
}