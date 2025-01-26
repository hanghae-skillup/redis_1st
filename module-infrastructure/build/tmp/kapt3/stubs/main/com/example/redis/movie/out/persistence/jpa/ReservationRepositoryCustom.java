package com.example.redis.movie.out.persistence.jpa;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H&\u00a8\u0006\n"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/ReservationRepositoryCustom;", "", "findByUserId", "", "Lcom/example/redis/movie/out/persistence/jpa/ReservationEntity;", "screeningId", "", "userId", "findSeatsByIds", "seatIds", "module-infrastructure"})
public abstract interface ReservationRepositoryCustom {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.redis.movie.out.persistence.jpa.ReservationEntity> findByUserId(long screeningId, long userId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.redis.movie.out.persistence.jpa.ReservationEntity> findSeatsByIds(long screeningId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> seatIds);
}