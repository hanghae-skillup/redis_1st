package com.example.redis.movie.out.persistence.jpa;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "movie")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0011R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001f\u00a8\u0006!"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "Lcom/example/redis/cmmn/BaseEntity;", "title", "", "thumbnailImagePath", "runningTime", "", "releaseDate", "Ljava/time/LocalDateTime;", "filmRatings", "Lcom/example/redis/movie/out/persistence/FilmRatings;", "movieGenre", "", "Lcom/example/redis/movie/out/persistence/jpa/MovieGenreEntity;", "movieTheaters", "Lcom/example/redis/movie/out/persistence/jpa/MovieTheaterEntity;", "id", "(Ljava/lang/String;Ljava/lang/String;JLjava/time/LocalDateTime;Lcom/example/redis/movie/out/persistence/FilmRatings;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getFilmRatings", "()Lcom/example/redis/movie/out/persistence/FilmRatings;", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovieGenre", "()Ljava/util/List;", "getMovieTheaters", "getReleaseDate", "()Ljava/time/LocalDateTime;", "getRunningTime", "()J", "getThumbnailImagePath", "()Ljava/lang/String;", "getTitle", "module-infrastructure"})
public final class MovieEntity extends com.example.redis.cmmn.BaseEntity {
    @jakarta.persistence.Column(name = "title")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @jakarta.persistence.Column(name = "thumbnail_image_path")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumbnailImagePath = null;
    @jakarta.persistence.Column(name = "running_time")
    private final long runningTime = 0L;
    @jakarta.persistence.Column(name = "release_date")
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime releaseDate = null;
    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(name = "film_ratings")
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.out.persistence.FilmRatings filmRatings = null;
    @org.hibernate.annotations.BatchSize(size = 1000)
    @jakarta.persistence.OneToMany(mappedBy = "movie", fetch = jakarta.persistence.FetchType.LAZY, cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.out.persistence.jpa.MovieGenreEntity> movieGenre = null;
    @org.hibernate.annotations.BatchSize(size = 1000)
    @jakarta.persistence.OneToMany(mappedBy = "movie", fetch = jakarta.persistence.FetchType.LAZY, cascade = {jakarta.persistence.CascadeType.ALL}, orphanRemoval = true)
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity> movieTheaters = null;
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "movie_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    
    public MovieEntity(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String thumbnailImagePath, long runningTime, @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime releaseDate, @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.out.persistence.FilmRatings filmRatings, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.out.persistence.jpa.MovieGenreEntity> movieGenre, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.out.persistence.jpa.MovieTheaterEntity> movieTheaters, @org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThumbnailImagePath() {
        return null;
    }
    
    public final long getRunningTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime getReleaseDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.out.persistence.FilmRatings getFilmRatings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.out.persistence.jpa.MovieGenreEntity> getMovieGenre() {
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