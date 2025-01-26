package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B8\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u0013\b\u0001\u0010\u0006\u001a\r\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\t0\u0007\u00a2\u0006\u0002\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR!\u0010\u0006\u001a\r\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\t0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieReserveRequestDto;", "", "movieId", "", "userId", "screeningId", "seats", "", "Lcom/example/redis/movie/in/dto/SeatRequestDto;", "Ljakarta/validation/Valid;", "(JJJLjava/util/List;)V", "getMovieId", "()J", "getScreeningId", "getSeats", "()Ljava/util/List;", "getUserId", "module-infrastructure"})
public final class MovieReserveRequestDto {
    @jakarta.validation.constraints.NotNull()
    private final long movieId = 0L;
    @jakarta.validation.constraints.NotNull()
    private final long userId = 0L;
    @jakarta.validation.constraints.NotNull()
    private final long screeningId = 0L;
    @jakarta.validation.Valid()
    @jakarta.validation.constraints.Size(min = 1, max = 5, message = "The seat must have one to five.")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.in.dto.SeatRequestDto> seats = null;
    
    public MovieReserveRequestDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "movie_id")
    long movieId, @com.fasterxml.jackson.annotation.JsonProperty(value = "user_id")
    long userId, @com.fasterxml.jackson.annotation.JsonProperty(value = "screening_id")
    long screeningId, @com.fasterxml.jackson.annotation.JsonProperty(value = "seats")
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