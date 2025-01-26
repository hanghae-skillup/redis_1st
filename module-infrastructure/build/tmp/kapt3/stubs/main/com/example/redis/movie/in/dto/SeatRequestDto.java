package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\r"}, d2 = {"Lcom/example/redis/movie/in/dto/SeatRequestDto;", "", "seatId", "", "seatRow", "", "seatCol", "(JLjava/lang/String;Ljava/lang/String;)V", "getSeatCol", "()Ljava/lang/String;", "getSeatId", "()J", "getSeatRow", "module-infrastructure"})
public final class SeatRequestDto {
    @jakarta.validation.constraints.NotNull()
    private final long seatId = 0L;
    @jakarta.validation.constraints.NotNull()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String seatRow = null;
    @jakarta.validation.constraints.NotNull()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String seatCol = null;
    
    public SeatRequestDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "seat_id")
    long seatId, @com.fasterxml.jackson.annotation.JsonProperty(value = "seat_row")
    @org.jetbrains.annotations.NotNull()
    java.lang.String seatRow, @com.fasterxml.jackson.annotation.JsonProperty(value = "seat_col")
    @org.jetbrains.annotations.NotNull()
    java.lang.String seatCol) {
        super();
    }
    
    public final long getSeatId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSeatRow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSeatCol() {
        return null;
    }
}