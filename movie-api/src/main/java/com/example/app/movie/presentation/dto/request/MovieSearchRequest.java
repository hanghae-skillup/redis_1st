package com.example.app.movie.presentation.dto.request;

import com.example.app.movie.type.MovieGenre;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.hibernate.validator.constraints.Length;

import static com.example.app.movie.out.persistence.entity.QMovieJpaEntity.movieJpaEntity;
import static java.util.Objects.nonNull;

public record MovieSearchRequest(
        @Length(max = 255, message = "제목은 255자 이하로 검색해주세요")
        String title,
        MovieGenre genre
){
    public Predicate toPredicate() {
        return ExpressionUtils.allOf(
                nonNull(title) ? movieJpaEntity.title.contains(title) : null,
                nonNull(genre) ? movieJpaEntity.genre.eq(genre) : null
        );
    }
}