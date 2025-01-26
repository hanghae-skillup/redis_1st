package com.example.redis.movie.out.persistence.adapters;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/redis/movie/out/persistence/adapters/MovieAdapter;", "Lcom/example/redis/movie/out/MoviePort;", "movieRepository", "Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;", "screeningRepository", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningRepository;", "reservationRepository", "Lcom/example/redis/movie/out/persistence/jpa/ReservationRepository;", "(Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;Lcom/example/redis/movie/out/persistence/jpa/ScreeningRepository;Lcom/example/redis/movie/out/persistence/jpa/ReservationRepository;)V", "findById", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "movieId", "", "findByOrderByReleaseDateDesc", "", "Lcom/example/redis/movie/Movie;", "title", "", "genre", "findScreeningById", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "screeningId", "reserve", "Lcom/example/redis/movie/ReservationReceipt;", "reservation", "Lcom/example/redis/movie/Reservation;", "module-infrastructure"})
public class MovieAdapter implements com.example.redis.movie.out.MoviePort {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.ScreeningRepository screeningRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.ReservationRepository reservationRepository = null;
    
    public MovieAdapter(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.ScreeningRepository screeningRepository, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.ReservationRepository reservationRepository) {
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
    public com.example.redis.movie.ReservationReceipt reserve(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.Reservation reservation) {
        return null;
    }
}