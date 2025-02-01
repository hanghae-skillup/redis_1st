package com.example.application.port.in;

import com.example.application.dto.request.MovieRequestDto;
import com.example.application.dto.request.MovieSearchCriteria;
import com.example.application.dto.request.ScreeningRequestDto;
import com.example.application.dto.response.MovieResponseDto;
import java.util.List;

public interface MovieServicePort {
    List<MovieResponseDto> findMovies(MovieSearchCriteria movieSearchCriteria);
    void createMovie(MovieRequestDto movieRequestDto);
    void addScreeningToMovie(Long movieId, ScreeningRequestDto screeningRequestDto);
}
