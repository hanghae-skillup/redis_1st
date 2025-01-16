package com.example.repository.movie;

import com.example.entity.Genre;
import com.example.entity.Movie;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.entity.QMovie.movie;
import static com.example.entity.QMovieTheater.movieTheater;
import static com.example.entity.QScreening.screening;
import static com.example.entity.QTheater.theater;

@Slf4j
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MovieRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Movie> findAllByTitleAndGenre(String title, String genre) {
        return queryFactory.selectFrom(movie)
                .where(titleLike(title), genreEq(genre))
                .join(movie.screenings, screening).fetchJoin()
                .join(movie.movieTheaters, movieTheater).fetchJoin()
                .join(movieTheater.theater, theater).fetchJoin()
                .orderBy(movie.releaseDate.desc())
                .fetch();
    }

    private Predicate titleLike(String title) {
        if (title == null) {
            return null;
        }
        return movie.title.like(title);
    }

    private Predicate genreEq(String genre) {
        if (genre == null) {
            return null;
        }
        return movie.genre.eq(Genre.valueOf(genre.toUpperCase()));
    }
}
