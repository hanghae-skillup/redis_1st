package com.example.redis.movie.out.persistence.adapters;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/redis/movie/out/persistence/adapters/MovieAdapter;", "Lcom/example/redis/movie/out/movie/MoviePort;", "movieRepository", "Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;", "screeningRepository", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningRepository;", "reservationRepository", "Lcom/example/redis/movie/out/persistence/jpa/ReservationRepository;", "theaterAdapter", "Lcom/example/redis/theater/out/persistence/adapters/TheaterAdapter;", "(Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;Lcom/example/redis/movie/out/persistence/jpa/ScreeningRepository;Lcom/example/redis/movie/out/persistence/jpa/ReservationRepository;Lcom/example/redis/theater/out/persistence/adapters/TheaterAdapter;)V", "findById", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "movieId", "", "findByOrderByReleaseDateDesc", "", "Lcom/example/redis/movie/Movie;", "title", "", "genre", "findScreeningById", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "screeningId", "reserve", "reservation", "Lcom/example/redis/movie/Reservation;", "module-infrastructure"})
public class MovieAdapter implements com.example.redis.movie.out.movie.MoviePort {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.ScreeningRepository screeningRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.ReservationRepository reservationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.theater.out.persistence.adapters.TheaterAdapter theaterAdapter = null;
    
    public MovieAdapter(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.ScreeningRepository screeningRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.ReservationRepository reservationRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.theater.out.persistence.adapters.TheaterAdapter theaterAdapter) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.redis.movie.Movie> findByOrderByReleaseDateDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.example.redis.movie.out.persistence.jpa.MovieEntity findById(long movieId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.example.redis.movie.out.persistence.jpa.ScreeningEntity findScreeningById(long screeningId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String reserve(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.Reservation reservation) {
        return null;
    }
}