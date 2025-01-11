package com.hh.application.movie;

import com.hh.domain.dto.ScreenDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
  public List<ScreenDto> getMovieScreen();

}
