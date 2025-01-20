package com.hh.application.movie;

import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.MovieScreeningDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

  public Page<MovieScreeningDto> findMoviesWithGroupConcat(Pageable pageable, String title, Genre genre);

}
