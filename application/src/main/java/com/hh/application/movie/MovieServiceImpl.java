package com.hh.application.movie;

import com.hh.domain.movie.dto.MovieDto;
import com.hh.domain.movie.dto.ScreenDto;
import com.hh.domain.movie.Screen;
import com.hh.infrastructure.movie.MovieRepository;
import com.hh.infrastructure.movie.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final ScreenRepository screenRepository;

  public List<ScreenDto> getMovieScreen() {

    List<Screen> MovieScreenList = screenRepository.findAll();
    return MovieScreenList.stream().map(ScreenDto::from).toList();
  }

  @Override
  public List<MovieDto> getMovies() {
    return movieRepository.findAll().stream().map(MovieDto::from).toList();
  }
}
