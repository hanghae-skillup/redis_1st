package com.bmsnc.adapter.in.web;

import com.bmsnc.applicaion.domain.service.MovieUseCaseService;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.common.Result;
import com.bmsnc.common.dto.MovieGenre;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    public Result searchRunningMovies(@RequestParam("theaterId") Long theaterId,
                                      @Size(max = 255) @RequestParam(value = "movieName", required = false) String movieName,
                                      @RequestParam(value = "genre", required = false) String genre) {

        MovieGenre movieGenre =  MovieGenre.anyMatch(genre) ? MovieGenre.valueOf(genre) : MovieGenre.ETC;

        RunningMovieCommand command = RunningMovieCommand.builder()
                .theaterId(theaterId)
                .movieName(movieName)
                .movieGenre(movieGenre)
                .build();
        return movieUseCaseService.searchRunningMovies(command);
    }

}
