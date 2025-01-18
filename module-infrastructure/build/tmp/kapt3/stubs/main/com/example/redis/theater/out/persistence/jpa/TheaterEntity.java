package com.example.redis.theater.out.persistence.jpa;

@jakarta.persistence.Table(name = "theater")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010\u00a8\u0006\u0014"}, d2 = {"Lcom/example/redis/theater/out/persistence/jpa/TheaterEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "name", "", "seats", "", "Lcom/example/redis/theater/out/persistence/jpa/SeatEntity;", "movieTheaters", "Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;", "id", "", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovieTheaters", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "getSeats", "module-infrastructure"})
public final class TheaterEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Column(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.hibernate.annotations.BatchSize(size = 1000)
    @jakarta.persistence.OneToMany(mappedBy = "theater", fetch = jakarta.persistence.FetchType.LAZY, cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.theater.out.persistence.jpa.SeatEntity> seats = null;
    @org.hibernate.annotations.BatchSize(size = 1000)
    @jakarta.persistence.OneToMany(mappedBy = "theater", fetch = jakarta.persistence.FetchType.LAZY, cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity> movieTheaters = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "theater_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public TheaterEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.theater.out.persistence.jpa.SeatEntity> seats, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity> movieTheaters, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.theater.out.persistence.jpa.SeatEntity> getSeats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity> getMovieTheaters() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
}