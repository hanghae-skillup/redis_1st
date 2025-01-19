package com.bmsnc.adapter.in.web;

import com.bmsnc.applicaion.domain.service.MovieUseCaseService;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.common.Result;
import com.bmsnc.common.dto.MovieGenre;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieUseCaseService movieUseCaseService;

    @GetMapping("/running/{theaterId}")
    public Result getRunningMovies(@PathVariable("theaterId") Long theaterId) {
        RunningMovieCommand command = RunningMovieCommand.builder()
                .theaterId(theaterId)
                .build();

        return movieUseCaseService.getRunningMovies(command);
    }

    // QueryDsl
    @GetMapping("/searchRunningMovies")
    public Result searchRunningMovies(@Valid SearchRunningMoviesRequest request) {
        MovieGenre movieGenre =  MovieGenre.anyMatch(request.getMovieGenre()) ? MovieGenre.valueOf(request.getMovieGenre()) : MovieGenre.ETC;

        RunningMovieCommand command = RunningMovieCommand.builder()
                .theaterId(request.getTheaterId())
                .movieName(request.getMovieName())
                .movieGenre(movieGenre)
                .build();
        return movieUseCaseService.searchRunningMovies(command);
    }

}
