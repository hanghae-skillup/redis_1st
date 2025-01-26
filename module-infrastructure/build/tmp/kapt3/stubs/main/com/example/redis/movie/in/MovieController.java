package com.example.redis.movie.in;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/api/v1/movies"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0017J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\b\b\u0001\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/redis/movie/in/MovieController;", "", "movieUseCase", "Lcom/example/redis/movie/in/MovieUseCase;", "reserveFacade", "Lcom/example/redis/reserve/in/ReserveUseCase;", "(Lcom/example/redis/movie/in/MovieUseCase;Lcom/example/redis/reserve/in/ReserveUseCase;)V", "gets", "Lorg/springframework/http/ResponseEntity;", "", "Lcom/example/redis/movie/in/dto/MovieResponseDto;", "movieSearchRequestQuery", "Lcom/example/redis/movie/in/dto/MovieSearchRequestQueryDto;", "reserve", "", "movieId", "", "body", "Lcom/example/redis/movie/in/dto/MovieReserveRequestDto;", "module-infrastructure"})
public class MovieController {
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.movie.in.MovieUseCase movieUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.redis.reserve.in.ReserveUseCase reserveFacade = null;
    
    public MovieController(@org.jetbrains.annotations.NotNull()
    com.example.redis.movie.in.MovieUseCase movieUseCase, @org.jetbrains.annotations.NotNull()
    com.example.redis.reserve.in.ReserveUseCase reserveFacade) {
        super();
    }
    
    @org.springframework.web.bind.annotation.GetMapping()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<java.util.List<com.example.redis.movie.in.dto.MovieResponseDto>> gets(@org.springframework.web.bind.annotation.ModelAttribute()
    @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.in.dto.MovieSearchRequestQueryDto movieSearchRequestQuery) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/{movieId}/reserve"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<kotlin.Unit> reserve(@org.springframework.web.bind.annotation.PathVariable()
    long movieId, @org.springframework.web.bind.annotation.RequestBody()
    @jakarta.validation.Valid()
    @org.jetbrains.annotations.NotNull()
    com.example.redis.movie.in.dto.MovieReserveRequestDto body) {
        return null;
    }
}