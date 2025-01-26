package com.example.redis.theater.out.persistence.adapters;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/redis/theater/out/persistence/adapters/TheaterAdapter;", "Lcom/example/redis/movie/out/TheaterPort;", "theaterRepository", "Lcom/example/redis/theater/out/persistence/jpa/TheaterRepository;", "seatRepository", "Lcom/example/redis/theater/out/persistence/jpa/SeatRepository;", "(Lcom/example/redis/theater/out/persistence/jpa/TheaterRepository;Lcom/example/redis/theater/out/persistence/jpa/SeatRepository;)V", "findSeatsByIds", "", "Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "seats", "Lcom/example/redis/theater/Seat;", "module-infrastructure"})
public class TheaterAdapter implements com.example.redis.movie.out.TheaterPort {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.TheaterRepository theaterRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.SeatRepository seatRepository = null;
    
    public TheaterAdapter(@org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.TheaterRepository theaterRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.SeatRepository seatRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.redis.theater.out.persistence.jpa.SeatEntity> findSeatsByIds(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.theater.Seat> seats) {
        return null;
    }
}