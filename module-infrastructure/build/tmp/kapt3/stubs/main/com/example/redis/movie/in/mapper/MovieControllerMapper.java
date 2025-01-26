package com.example.redis.movie.in.mapper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/redis/movie/in/mapper/MovieControllerMapper;", "", "()V", "Companion", "module-infrastructure"})
public final class MovieControllerMapper {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.in.mapper.MovieControllerMapper.Companion Companion = null;
    
    public MovieControllerMapper() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0002\u00a8\u0006\n"}, d2 = {"Lcom/example/redis/movie/in/mapper/MovieControllerMapper$Companion;", "", "()V", "toReservationDomain", "Lcom/example/redis/movie/Reservation;", "dto", "Lcom/example/redis/movie/in/dto/MovieReserveRequestDto;", "toSeatDomain", "Lcom/example/redis/theater/Seat;", "Lcom/example/redis/movie/in/dto/SeatRequestDto;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.Reservation toReservationDomain(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.in.dto.MovieReserveRequestDto dto) {
            return null;
        }
        
        private final com.example.redis.theater.Seat toSeatDomain(com.example.redis.movie.in.dto.SeatRequestDto dto) {
            return null;
        }
    }
}