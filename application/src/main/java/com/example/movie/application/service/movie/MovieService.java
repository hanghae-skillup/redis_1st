package com.example.movie.application.service.movie;

import com.example.movie.application.service.movie.dto.MovieResult;
import java.util.List;

public interface MovieService {

    /**
     * 영화 목록 조회
     */
    List<MovieResult>  searchMovies();
}
