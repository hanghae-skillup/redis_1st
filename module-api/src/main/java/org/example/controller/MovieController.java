package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.baseresponse.BaseResponse;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.exception.RateLimitExceededException;
import org.example.service.RedisRateLimiterService;
import org.example.service.movie.MovieService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.baseresponse.BaseResponseStatus.TOO_MANY_REQUEST_ERROR;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final RedisRateLimiterService redisRateLimiterService;

    @GetMapping("/movies/playing")
    public BaseResponse<List<PlayingMoviesResponseDto>> getPlayingMovies(@ModelAttribute @Validated MoviesFilterRequestDto moviesFilterRequestDto, HttpServletRequest request) {
        String clientIp = getClientIP(request);

        // IP 차단 여부 확인
        Long allowed = redisRateLimiterService.isAllowed(clientIp);
        if (allowed == -1) {
            throw new RateLimitExceededException(TOO_MANY_REQUEST_ERROR);
        }

        List<PlayingMoviesResponseDto> playingMovies = movieService.getPlayingMovies(moviesFilterRequestDto);
        return new BaseResponse<>(playingMovies);
    }

    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
