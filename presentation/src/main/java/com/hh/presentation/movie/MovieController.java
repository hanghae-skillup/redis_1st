package com.hh.presentation.movie;


import com.hh.application.movie.MovieService;
import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.response.MovieScreeningResponse;
import com.hh.domain.movie.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public Response<Page<MovieScreeningResponse>> screenList(
         @PageableDefault(page = 0, size = 10) Pageable pageable,
         @RequestParam(required = false)String title,
         @RequestParam(required = false) String genre
  ){

    return Response.success(movieService.findMoviesWithGroupConcat(
            pageable,
            title,
            genre != null ? Genre.valueOf(genre):null)
            .map(MovieScreeningResponse::from));
  }
}
