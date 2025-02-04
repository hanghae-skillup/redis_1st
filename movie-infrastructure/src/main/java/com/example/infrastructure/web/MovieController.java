package com.example.infrastructure.web;

import com.example.application.dto.request.MovieRequestDto;
import com.example.application.dto.request.MovieSearchCriteria;
import com.example.application.dto.request.ScreeningRequestDto;
import com.example.application.dto.response.MovieResponseDto;
import com.example.application.port.in.MovieServicePort;
import com.example.application.service.MovieSearchRateLimiterService;
import com.example.domain.exception.CustomException;
import com.example.domain.exception.ErrorCode;
import com.example.domain.validation.MovieValidation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieServicePort movieServicePort;
    private final MovieValidation movieValidation;
    private final MovieSearchRateLimiterService movieSearchRateLimiterService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getMovies(
            @ModelAttribute MovieSearchCriteria criteria,
            HttpServletRequest request
    ) {
        String clientIp = request.getHeader("X-Forwarded-For"); // 프록시 서버 사용 시에도 실제 클라이언트 ip 반환하도록 헤더 값을 읽음
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }

        if (!movieSearchRateLimiterService.isAllowed(clientIp)) {
            throw new CustomException(ErrorCode.RATE_LIMIT_EXCEED);
        }

        movieValidation.validateTitleLength(criteria.title());

        List<MovieResponseDto> movies = movieServicePort.findMovies(criteria);
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<List<MovieResponseDto>> createMovie(
            @RequestBody @Valid MovieRequestDto movieRequestDto) {
        movieValidation.validateTitleLength(movieRequestDto.title());
        movieServicePort.createMovie(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{movieId}/screenings")
    public ResponseEntity<Void> createScreening(
            @PathVariable Long movieId,
            @RequestBody @Valid ScreeningRequestDto screeningRequestDto
    ) {
        movieServicePort.addScreeningToMovie(movieId, screeningRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
