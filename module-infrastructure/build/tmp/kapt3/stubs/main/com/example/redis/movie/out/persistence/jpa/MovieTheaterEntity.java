package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Table(name = "movie_theater")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "movie", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "theater", "Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "screeningSchedules", "", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningScheduleEntity;", "id", "", "(Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;Ljava/util/List;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovie", "()Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "getScreeningSchedules", "()Ljava/util/List;", "getTheater", "()Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "module-infrastructure"})
public final class MovieTheaterEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "movie_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieEntity movie = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "theater_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.jpa.TheaterEntity theater = null;
    @org.hibernate.annotations.BatchSize(size = 1000)
    @jakarta.persistence.OneToMany(mappedBy = "movieTheater", fetch = jakarta.persistence.FetchType.LAZY, cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.out.persistence.jpa.ScreeningScheduleEntity> screeningSchedules = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "movie_theater_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public MovieTheaterEntity(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieEntity movie, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.jpa.TheaterEntity theater, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.out.persistence.jpa.ScreeningScheduleEntity> screeningSchedules, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
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
    public final java.util.List<com.example.redis.movie.out.persistence.jpa.ScreeningScheduleEntity> getScreeningSchedules() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
}