package com.example.app.movie.out.persistence.repository;

import com.example.app.movie.out.persistence.entity.MovieEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.app.movie.out.persistence.entity.QMovieEntity.movieEntity;
import static com.example.app.movie.out.persistence.entity.QMovieTheaterEntity.movieTheaterEntity;
import static com.example.app.movie.out.persistence.entity.QShowtimeEntity.showtimeEntity;

@RequiredArgsConstructor
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MovieEntity> findAllBy(Predicate predicate) {
        return queryFactory
                .select(movieEntity)
                .from(movieEntity)
                .leftJoin(movieEntity.showtimes, showtimeEntity).fetchJoin()
                .leftJoin(movieEntity.movieTheaters, movieTheaterEntity).fetchJoin()
                .leftJoin(movieTheaterEntity.theater).fetchJoin()
                .where(predicate)
                .orderBy(movieEntity.releaseDate.desc())
                .fetch();
    }
}
