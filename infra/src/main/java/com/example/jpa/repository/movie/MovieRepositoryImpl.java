package com.example.jpa.repository.movie;

import com.example.jpa.entity.movie.Genre;
import com.example.jpa.entity.movie.QMovie;
import com.example.jpa.entity.movie.QScreening;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;
import com.example.jpa.repository.movie.dto.QMoviesDetailDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.jpa.entity.movie.QMovie.movie;
import static com.example.jpa.entity.movie.QScreening.screening;
import static com.example.jpa.entity.theater.QTheater.theater;

public class MovieRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MovieRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MoviesDetailDto> searchWithFiltering(LocalDateTime now, Genre genre, String title) {
        return queryFactory
                .select(new QMoviesDetailDto(
                        movie.id,
                        movie.name,
                        movie.grade,
                        movie.releaseDate,
                        movie.thumbnail,
                        movie.runningTime,
                        movie.genre,
                        theater.id,
                        theater.name,
                        screening.startAt,
                        screening.endAt
                ))
                .from(movie)
                .join(screening).on(movie.id.eq(screening.movieId))
                .join(theater).on(screening.theaterId.eq(theater.id))
                .where(
                        startAtAfter(now),
                        genreEquals(genre),
                        nameContains(title)
                )
                .fetch();
    }

    private BooleanExpression startAtAfter(LocalDateTime now) {
        return now != null ? QScreening.screening.startAt.goe(now) : null;
    }

    private BooleanExpression genreEquals(Genre genre) {
        return genre != null ? QMovie.movie.genre.eq(genre) : null;
    }

    private BooleanExpression nameContains(String title) {
        return title != null ? QMovie.movie.name.containsIgnoreCase(title) : null;
    }
}
