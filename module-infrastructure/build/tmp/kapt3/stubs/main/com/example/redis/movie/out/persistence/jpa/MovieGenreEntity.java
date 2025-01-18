package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Table(name = "movie_genre")
@jakarta.persistence.Entity()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieGenreEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "name", "", "movie", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "id", "", "(Ljava/lang/String;Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;Ljava/lang/Long;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovie", "()Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "getName", "()Ljava/lang/String;", "module-infrastructure"})
public final class MovieGenreEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Column(name = "name")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "movie_id")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.jpa.MovieEntity movie = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "movie_genre_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public MovieGenreEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.jpa.MovieEntity movie, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.out.persistence.jpa.MovieEntity getMovie() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getId() {
        return null;
    }
}