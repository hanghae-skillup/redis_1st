package com.hh.presentation.movie;


import com.hh.application.movie.MovieService;
import com.hh.presentation.response.MovieScreeningResponse;
import com.hh.presentation.response.Response;
import com.hh.presentation.response.ScreenResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public Response<List<MovieScreeningResponse>> screenList(){

    return Response.success(movieService.findMoviesWithGroupConcat().stream().map(MovieScreeningResponse::from).toList());
  }
}
