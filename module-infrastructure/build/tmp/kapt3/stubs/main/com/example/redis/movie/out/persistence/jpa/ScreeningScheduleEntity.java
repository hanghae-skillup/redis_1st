package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Table(name = "screening_schedule")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/ScreeningScheduleEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "startTime", "Ljava/time/LocalDateTime;", "endTime", "movieTheater", "Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;", "id", "", "(Ljava/time/LocalDateTime;Ljava/time/LocalDateTime;Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;Ljava/lang/Long;)V", "getEndTime", "()Ljava/time/LocalDateTime;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovieTheater", "()Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;", "getStartTime", "module-infrastructure"})
public final class ScreeningScheduleEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Column(name = "start_time")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime startTime = null;
    @jakarta.persistence.Column(name = "end_time")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime endTime = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "movie_theater_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity movieTheater = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "screening_schedule_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public ScreeningScheduleEntity(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime startTime, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime endTime, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity movieTheater, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
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
    public final com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity getMovieTheater() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
}