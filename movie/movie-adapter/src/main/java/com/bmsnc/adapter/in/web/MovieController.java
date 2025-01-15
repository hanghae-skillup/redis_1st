package com.bmsnc.adapter.in.web;

import com.bmsnc.applicaion.domain.service.MovieUseCaseService;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;
import com.bmsnc.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .now(LocalDateTime.now())
                .build();

        return movieUseCaseService.getRunningMovies(command);
    }

}
