package com.example.app.movie.out.persistence.repository;

import com.example.app.movie.out.persistence.entity.MovieJpaEntity;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.app.movie.out.persistence.entity.QMovieJpaEntity.movieJpaEntity;
import static com.example.app.movie.out.persistence.entity.QMovieTheaterJpaEntity.movieTheaterJpaEntity;
import static com.example.app.movie.out.persistence.entity.QShowtimeJpaEntity.showtimeJpaEntity;

@RequiredArgsConstructor
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MovieJpaEntity> findAllBy(Predicate predicate) {
        return queryFactory
                .select(movieJpaEntity)
                .from(movieJpaEntity)
                .leftJoin(movieJpaEntity.showtimes, showtimeJpaEntity).fetchJoin()
                .leftJoin(movieJpaEntity.movieTheaters, movieTheaterJpaEntity).fetchJoin()
                .leftJoin(movieTheaterJpaEntity.theater).fetchJoin()
                .where(predicate)
                .orderBy(movieJpaEntity.releaseDate.desc())
                .fetch();
    }
}
