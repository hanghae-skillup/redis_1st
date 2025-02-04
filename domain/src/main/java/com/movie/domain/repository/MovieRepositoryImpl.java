package com.movie.domain.repository;

import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.movie.domain.entity.QMovie.movie;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;
    
    @Override
    public List<Movie> findNowShowingMovies(MovieSearchCondition condition) {
        return queryFactory
                .selectFrom(movie)
                .where(
                        titleContains(condition.getTitle()),
                        genreEquals(condition.getGenre())
                )
                .fetch();
    }
    
    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? movie.title.contains(title) : null;
    }
    
    private BooleanExpression genreEquals(String genre) {
        return StringUtils.hasText(genre) ? movie.genre.eq(genre) : null;
    }
} 