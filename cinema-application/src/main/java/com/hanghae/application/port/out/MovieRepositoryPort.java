package com.hanghae.application.port.out;

import com.hanghae.application.projection.MovieScheduleProjection;

import java.util.List;

public interface MovieRepositoryPort {
    List<MovieScheduleProjection> findShowingMovieSchedules();
}
