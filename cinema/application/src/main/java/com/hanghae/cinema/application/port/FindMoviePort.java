package com.hanghae.cinema.application.port;

import com.hanghae.cinema.domain.model.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface FindMoviePort {

    List<Movie> getMovieList(LocalDate today);
}
