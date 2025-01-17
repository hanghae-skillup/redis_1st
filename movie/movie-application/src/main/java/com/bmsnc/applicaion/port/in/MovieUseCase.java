package com.bmsnc.applicaion.port.in;



import com.bmsnc.common.Result;

public interface MovieUseCase {

    Result getRunningMovies(RunningMovieCommand command);
    Result searchRunningMovies(RunningMovieCommand command);

}
