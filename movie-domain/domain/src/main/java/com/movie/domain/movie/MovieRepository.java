package com.movie.domain.movie;

import com.movie.domain.movie.dto.command.MovieCommand;
import com.movie.domain.movie.dto.info.MovieInfo;

import java.util.List;

public interface MovieRepository {

    List<MovieInfo.Get> getMoviesBySearch(MovieCommand.Search search);

}
