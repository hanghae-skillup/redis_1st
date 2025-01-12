package com.hh.application.movie;

import com.hh.domain.movie.dto.MovieDto;
import com.hh.domain.movie.dto.ScreenDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
  public List<ScreenDto> getMovieScreen();

  public List<MovieDto> getMovies();

}
