package com.movie.infra.repository;

import com.movie.domain.dto.MovieProjection;
import com.movie.domain.dto.MovieSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.movie.domain.entity.QMovie.movie;

@Repository
@RequiredArgsConstructor
public class MovieQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<MovieProjection> search(MovieSearchCondition condition) {
        return queryFactory
                .select(Projections.fields(MovieProjection.class,
                        movie.id,
                        movie.title,
                        movie.thumbnail,
                        movie.runningTime,
                        movie.genre))
                .from(movie)
                .where(
                        titleEq(condition.getTitle()),
                        genreEq(condition.getGenre())
                )
                .orderBy(movie.releaseDate.desc())
                .fetch();
    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? movie.title.eq(title) : null;
    }

    private BooleanExpression genreEq(String genre) {
        return StringUtils.hasText(genre) ? movie.genre.eq(genre) : null;
    }
} 