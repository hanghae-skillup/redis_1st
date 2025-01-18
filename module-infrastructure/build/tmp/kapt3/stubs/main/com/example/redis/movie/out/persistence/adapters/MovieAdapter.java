package com.example.redis.movie.out.persistence.adapters;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/redis/movie/out/persistence/adapters/MovieAdapter;", "Lcom/example/redis/movie/out/movie/MoviePort;", "movieRepository", "Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;", "(Lcom/example/redis/movie/out/persistence/jpa/MovieRepository;)V", "findByOrderByReleaseDateDesc", "", "Lcom/example/redis/movie/Movie;", "title", "", "genre", "module-infrastructure"})
public class MovieAdapter implements com.example.redis.movie.out.movie.MoviePort {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository = null;
    
    public MovieAdapter(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieRepository movieRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.redis.movie.Movie> findByOrderByReleaseDateDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
}