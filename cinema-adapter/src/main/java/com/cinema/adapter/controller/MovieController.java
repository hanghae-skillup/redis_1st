package com.cinema.adapter.controller;

import com.cinema.application.dto.MovieRequestDTO;
import com.cinema.application.dto.MovieResponseDTO;
import com.cinema.application.dto.TicketRequestDTO;
import com.cinema.application.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    /**
     * 영화별 상영시간표 조회
     * */
    // TODO : 상영일자가 추가되는 경우, bookable 쿼리파라미터를 추가하여 상영가능한 상태의 영화만 조회할 수 있도록 확장 가능
    @GetMapping
    public List<MovieResponseDTO> getMovieScreenings(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {
        return movieService.getMovieScreenings(movieRequestDTO);
    }

    /**
     * 영화별 상영시간표 캐시 삭제
     */
    @GetMapping("/evictRedisCache")
    public void evictRedisCache() {
        movieService.evictShowingMovieCache();
    }
}
