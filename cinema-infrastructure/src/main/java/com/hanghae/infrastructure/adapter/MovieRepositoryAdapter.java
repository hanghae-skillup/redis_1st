package com.hanghae.infrastructure.adapter;

import com.hanghae.application.dto.MovieScheduleRequestDto;
import com.hanghae.application.port.out.MovieRepositoryPort;
import com.hanghae.application.projection.MovieScheduleProjection;
import com.hanghae.infrastructure.entity.*;
import com.hanghae.infrastructure.repository.MovieRepositoryJpa;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieRepositoryAdapter implements MovieRepositoryPort {
    private final JPAQueryFactory queryFactory;

    /**
     * 영화별 상영시간표 그룹핑하여 조회
     * showing_movies
     */
    @Cacheable(value = "showing_movie_schedules", key = "#requestDto?.title + '_' + #requestDto?.genre", unless = "#result == null or #result.isEmpty()")
    @Override
    public List<MovieScheduleProjection> findShowingMovieSchedules(MovieScheduleRequestDto requestDto) {
        // @Cacheable 사용으로 캐시를 사용한다.
        // * 캐시이름 : showing_movie_schedules
        // * 캐시키 : requestDto.title_requestDto.genre
        // * 제외조건 : 하단 DB조회 결과가 null 또는 존재하지 않으면(isEmpty) 저장 안함
        // 캐시에 존재하지 않으면 아래 로직을 실행한다.
        QMovieEntity movie = QMovieEntity.movieEntity;
        QScreeningScheduleEntity schedule = QScreeningScheduleEntity.screeningScheduleEntity;
        QScreenEntity screen = QScreenEntity.screenEntity;
        QUploadFileEntity uploadFile = QUploadFileEntity.uploadFileEntity;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(schedule.showStartAt.after(LocalDateTime.now()));

        if(requestDto != null) {
            if (requestDto.getTitle() != null) {
                builder.and(movie.title.like(requestDto.getTitle() + "%"));
            }
            if (requestDto.getGenre() != null) {
                builder.and(movie.genre.eq(requestDto.getGenre()));
            }
        }

        return queryFactory
                .select(Projections.bean(
                        MovieScheduleProjection.class,
                        movie.id,
                        movie.title,
                        movie.rating,
                        movie.releaseDate,
                        uploadFile.filePath.concat("/").concat(uploadFile.fileName).as("thumbnail"),
                        movie.runningTimeMinutes,
                        movie.genre,
                        screen.screenName,
                        schedule.showStartAt
                ))
                .from(movie)
                .innerJoin(schedule).on(schedule.movieEntity.id.eq(movie.id))
                .innerJoin(screen).on(screen.id.eq(schedule.screenEntity.id))
                .leftJoin(uploadFile).on(uploadFile.id.eq(movie.uploadFileEntity.id))
                .where(builder)
                .orderBy(movie.releaseDate.desc(), schedule.showStartAt.asc())
                .fetch();
    }

    /**
     * 영화별 상영시간표 그룹핑 캐시 삭제
     */
    @CacheEvict(value = "showing_movie_schedules", allEntries = true)
    public void evictShowingMovieCache() {
        // 캐시를 강제로 삭제
    }
}
