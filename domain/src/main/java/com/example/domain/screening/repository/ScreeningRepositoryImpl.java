package com.example.domain.screening.repository;

import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.dto.ProjectionScreeningResponseDto;
import com.example.domain.screening.entity.Screening;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.domain.movie.entity.QMovie.movie;
import static com.example.domain.screening.entity.QScreening.screening;
import static com.example.domain.theater.entity.QTheater.theater;

@RequiredArgsConstructor
public class ScreeningRepositoryImpl implements ScreeningRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProjectionScreeningResponseDto> findScreeningInfoAndSearchingByTitleAndGenre(
            LocalDateTime now, String title, Movie.Genre genre) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        ProjectionScreeningResponseDto.class,
                        movie.title,
                        movie.thumbnail,
                        movie.rating,
                        movie.releaseDate,
                        movie.runtimeMinutes,
                        movie.genre,
                        theater.name,
                        screening.startTime,
                        screening.endTime
                ))
                .from(screening)
                .join(screening.movie, movie)
                .join(screening.theater, theater)
                .where(
                        isStartTimeGoeToNow(now),
                        titleEq(title),
                        genreEq(genre)
                )
                .orderBy(screening.startTime.asc())
                .fetch();
    }


    private BooleanExpression isStartTimeGoeToNow(LocalDateTime now){
        return screening.startTime.goe(now);
    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? movie.title.eq(title) : null;
    }

    private BooleanExpression genreEq(Movie.Genre genre) {
        return genre != null ? movie.genre.eq(genre) : null;
    }
}
