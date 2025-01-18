package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+Ba\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u0012\u000e\b\u0003\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\f\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\nH\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\fH\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u00c6\u0003Je\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\n2\u000e\b\u0003\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u000e\b\u0003\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\fH\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011\u00a8\u0006,"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieResponseDto;", "", "id", "", "title", "", "filmRatings", "thumbnailImagePath", "runningTime", "releaseDate", "Ljava/time/LocalDateTime;", "movieGenre", "", "theaters", "Lcom/example/redis/movie/in/dto/MovieTheaterResponseDto;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/time/LocalDateTime;Ljava/util/List;Ljava/util/List;)V", "getFilmRatings", "()Ljava/lang/String;", "getId", "()J", "getMovieGenre", "()Ljava/util/List;", "getReleaseDate", "()Ljava/time/LocalDateTime;", "getRunningTime", "getTheaters", "getThumbnailImagePath", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "module-infrastructure"})
public final class MovieResponseDto {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String filmRatings = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumbnailImagePath = null;
    private final long runningTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.time.LocalDateTime releaseDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> movieGenre = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.redis.movie.in.dto.MovieTheaterResponseDto> theaters = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.in.dto.MovieResponseDto.Companion Companion = null;
    
    public MovieResponseDto(@com.fasterxml.jackson.annotation.JsonProperty(value = "id")
    long id, @com.fasterxml.jackson.annotation.JsonProperty(value = "title")
    @org.jetbrains.annotations.NotNull()
    java.lang.String title, @com.fasterxml.jackson.annotation.JsonProperty(value = "film_ratings")
    @org.jetbrains.annotations.NotNull()
    java.lang.String filmRatings, @com.fasterxml.jackson.annotation.JsonProperty(value = "thumbnail_image_path")
    @org.jetbrains.annotations.NotNull()
    java.lang.String thumbnailImagePath, @com.fasterxml.jackson.annotation.JsonProperty(value = "running_time")
    long runningTime, @com.fasterxml.jackson.annotation.JsonProperty(value = "release_date")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime releaseDate, @com.fasterxml.jackson.annotation.JsonProperty(value = "movie_genre")
    @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> movieGenre, @com.fasterxml.jackson.annotation.JsonProperty(value = "theaters")
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.in.dto.MovieTheaterResponseDto> theaters) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilmRatings() {
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
    public final java.util.List<java.lang.String> getMovieGenre() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.in.dto.MovieTheaterResponseDto> getTheaters() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDateTime component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.redis.movie.in.dto.MovieTheaterResponseDto> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.redis.movie.in.dto.MovieResponseDto copy(@com.fasterxml.jackson.annotation.JsonProperty(value = "id")
    long id, @com.fasterxml.jackson.annotation.JsonProperty(value = "title")
    @org.jetbrains.annotations.NotNull()
    java.lang.String title, @com.fasterxml.jackson.annotation.JsonProperty(value = "film_ratings")
    @org.jetbrains.annotations.NotNull()
    java.lang.String filmRatings, @com.fasterxml.jackson.annotation.JsonProperty(value = "thumbnail_image_path")
    @org.jetbrains.annotations.NotNull()
    java.lang.String thumbnailImagePath, @com.fasterxml.jackson.annotation.JsonProperty(value = "running_time")
    long runningTime, @com.fasterxml.jackson.annotation.JsonProperty(value = "release_date")
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd", shape = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING)
    @org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime releaseDate, @com.fasterxml.jackson.annotation.JsonProperty(value = "movie_genre")
    @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> movieGenre, @com.fasterxml.jackson.annotation.JsonProperty(value = "theaters")
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.redis.movie.in.dto.MovieTheaterResponseDto> theaters) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieResponseDto$Companion;", "", "()V", "toDto", "Lcom/example/redis/movie/in/dto/MovieResponseDto;", "movie", "Lcom/example/redis/movie/query/MovieProjection;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.in.dto.MovieResponseDto toDto(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.query.MovieProjection movie) {
            return null;
        }
    }
}