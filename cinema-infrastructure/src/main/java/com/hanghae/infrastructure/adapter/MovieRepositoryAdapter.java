package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.MovieRepositoryPort;
import com.hanghae.application.projection.MovieScheduleProjection;
import com.hanghae.infrastructure.entity.QMovieEntity;
import com.hanghae.infrastructure.entity.QScreenEntity;
import com.hanghae.infrastructure.entity.QScreeningScheduleEntity;
import com.hanghae.infrastructure.entity.QUploadFileEntity;
import com.hanghae.infrastructure.repository.MovieRepositoryJpa;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieRepositoryAdapter implements MovieRepositoryPort {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MovieScheduleProjection> findShowingMovieSchedules() {
        QMovieEntity movie = QMovieEntity.movieEntity;
        QScreeningScheduleEntity schedule = QScreeningScheduleEntity.screeningScheduleEntity;
        QScreenEntity screen = QScreenEntity.screenEntity;
        QUploadFileEntity uploadFile = QUploadFileEntity.uploadFileEntity;

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
                .where(schedule.showStartAt.after(LocalDateTime.now()))
                .orderBy(movie.releaseDate.desc(), schedule.showStartAt.asc())
                .fetch();
    }
}
