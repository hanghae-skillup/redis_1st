package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Table(name = "screening")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\u0002\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "id", "", "startTime", "Ljava/time/LocalDateTime;", "endTime", "movie", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "theater", "Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "reservations", "", "Lcom/example/redis/movie/out/persistence/jpa/ReservationEntity;", "(Ljava/lang/Long;Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;Ljava/util/List;)V", "getEndTime", "()Ljava/time/LocalDateTime;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovie", "()Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "getReservations", "()Ljava/util/List;", "getStartTime", "getTheater", "()Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "module-infrastructure"})
public final class ScreeningEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "screening_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(name = "start_time")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime startTime = null;
    @jakarta.persistence.Column(name = "end_time")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime endTime = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "movie_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieEntity movie = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "theater_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.TheaterEntity theater = null;
    @jakarta.persistence.OneToMany(mappedBy = "screening", fetch = jakarta.persistence.FetchType.LAZY)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.out.persistence.jpa.ReservationEntity> reservations = null;
    
    public ScreeningEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startTime, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endTime, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieEntity movie, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.TheaterEntity theater, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.out.persistence.jpa.ReservationEntity> reservations) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
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
    public final com.example.redis.movie.out.persistence.jpa.MovieEntity getMovie() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.theater.out.persistence.jpa.TheaterEntity getTheater() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.out.persistence.jpa.ReservationEntity> getReservations() {
        return null;
    }
}