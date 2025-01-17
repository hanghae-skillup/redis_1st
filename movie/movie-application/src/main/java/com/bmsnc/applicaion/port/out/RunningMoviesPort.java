package com.bmsnc.applicaion.port.out;

import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;

import java.util.List;

public interface RunningMoviesPort {

    List<MovieModel> getRunningMovies(RunningMovieCommand command);

    List<MovieModel> searchRunningMovies(RunningMovieCommand command);
}
