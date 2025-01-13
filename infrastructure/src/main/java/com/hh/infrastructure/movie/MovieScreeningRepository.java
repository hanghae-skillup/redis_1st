package com.hh.infrastructure.movie;

import com.hh.domain.movie.QScreen;
import com.hh.domain.movie.QTheater;
import com.hh.domain.movie.dto.MovieScreeningDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hh.domain.movie.QMovie.movie;
import static com.hh.domain.movie.QScreen.screen;
import static com.hh.domain.movie.QTheater.theater;

@Repository
@RequiredArgsConstructor
public class MovieScreeningRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public List<MovieScreeningDto> findMovieScreening(){
    return jpaQueryFactory.select(Projections.constructor(MovieScreeningDto.class,
              movie.title,
            movie.firmRating,
            movie.releasedDate,
            theater.name,
            movie.thumbnail,
            movie.runningTime,
            movie.genre,
            screen.startTime,
            screen.endTime
            ))
            .from(movie)
            .join(screen).on(movie.id.eq(screen.movieId))
            .join(theater).on(theater.id.eq(screen.theaterId))
            .fetch();


  }
}
