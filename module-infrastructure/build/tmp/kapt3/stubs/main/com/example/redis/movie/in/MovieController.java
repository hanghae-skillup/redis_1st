package com.example.redis.movie.in;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/api/v1/movies"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/redis/movie/in/MovieController;", "", "movieUseCase", "Lcom/example/redis/movie/in/movie/MovieUseCase;", "(Lcom/example/redis/movie/in/movie/MovieUseCase;)V", "gets", "Lorg/springframework/http/ResponseEntity;", "", "Lcom/example/redis/movie/in/dto/MovieResponseDto;", "title", "", "genre", "module-infrastructure"})
public class MovieController {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.in.movie.MovieUseCase movieUseCase = null;
    
    public MovieController(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.in.movie.MovieUseCase movieUseCase) {
        super();
    }
    
    @org.springframework.web.bind.annotation.GetMapping()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<java.util.List<com.example.redis.movie.in.dto.MovieResponseDto>> gets(@org.springframework.web.bind.annotation.RequestParam(value = "title", required = false)
    @jakarta.validation.Valid()
    @jakarta.validation.constraints.Size(message = "The title must be at least 1 character and no more than 197 characters long.", min = 0, max = 197)
    @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.springframework.web.bind.annotation.RequestParam(value = "genre", required = false)
    @jakarta.validation.Valid()
    @jakarta.validation.constraints.Size(message = "The genre must be at least 1 character and no more than 197 characters long.", min = 0, max = 197)
    @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
}