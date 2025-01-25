package com.example.redis.movie.in.dto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2 = {"Lcom/example/redis/movie/in/dto/MovieSearchRequestQueryDto;", "", "title", "", "genre", "(Ljava/lang/String;Ljava/lang/String;)V", "getGenre", "()Ljava/lang/String;", "getTitle", "module-infrastructure"})
public final class MovieSearchRequestQueryDto {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String genre = null;
    
    public MovieSearchRequestQueryDto(@org.springframework.web.bind.annotation.RequestParam(value = "title", required = false)
    @jakarta.validation.Valid()
    @jakarta.validation.constraints.Size(message = "The title must be at least 1 character and no more than 197 characters long.", min = 0, max = 197)
    @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.springframework.web.bind.annotation.RequestParam(value = "genre", required = false)
    @jakarta.validation.Valid()
    @jakarta.validation.constraints.Size(message = "The genre must be at least 1 character and no more than 197 characters long.", min = 0, max = 197)
    @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getGenre() {
        return null;
    }
}