package com.movie.storage.movie.repository;

import com.movie.storage.movie.dto.payload.*;
import com.movie.storage.movie.dto.statement.ScheduleStatement;
import com.movie.storage.movie.entity.ScheduleEntity;
import com.movie.storage.movie.entity.ScreenEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.movie.storage.movie.entity.QMovieEntity.movieEntity;
import static com.movie.storage.movie.entity.QScheduleEntity.scheduleEntity;
import static com.movie.storage.movie.entity.QScreenEntity.screenEntity;
import static com.movie.storage.movie.entity.QTheaterEntity.theaterEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

@Component
public class ScheduleJpaQuerySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ScheduleJpaQuerySupport(JPAQueryFactory queryFactory) {
        super(ScheduleEntity.class);
        this.queryFactory = queryFactory;
    }

    public List<SchedulePayload.Get> getSchedules(ScheduleStatement.Search search) {
        Map<ScreenEntity, SchedulePayload.Get> result = queryFactory.selectFrom(scheduleEntity)
                .innerJoin(theaterEntity).on(theaterEntity.eq(scheduleEntity.theater))
                .innerJoin(screenEntity).on(screenEntity.eq(scheduleEntity.screen))
                .innerJoin(movieEntity).on(movieEntity.eq(scheduleEntity.movie))
                .where(
                        searchConditions(search)
                )
                .orderBy(movieEntity.releasedAt.asc())
                .transform(groupBy(screenEntity).as(new QSchedulePayload_Get(
                        scheduleEntity.id,
                        new QTheaterPayload_Get(theaterEntity.id, theaterEntity.name),
                        new QScreenPayload_Get(screenEntity.id, screenEntity.name, screenEntity.theaterId),
                        new QMoviePayload_Get(
                                movieEntity.id, movieEntity.title, movieEntity.filmRating,
                                movieEntity.genre, movieEntity.releasedAt, movieEntity.thumbnailUrl,
                                movieEntity.runningTime
                        ),
                        list(new QTimeTablePayload_Get(
                                scheduleEntity.startTime, scheduleEntity.endTime
                        ))
                )));

        Map<Long, SchedulePayload.Get> uniqueMovies = new LinkedHashMap<>();
        result.values().forEach(schedule -> {
            Long movieId = schedule.getMovie().getId();
            uniqueMovies.putIfAbsent(movieId, schedule);
        });

        return new ArrayList<>(uniqueMovies.values());
    }

    public List<SchedulePayload.Get> getSchedules(Long theaterId) {
        Map<ScreenEntity, SchedulePayload.Get> result = queryFactory.selectFrom(scheduleEntity)
                .innerJoin(theaterEntity).on(theaterEntity.eq(scheduleEntity.theater))
                .innerJoin(screenEntity).on(screenEntity.eq(scheduleEntity.screen))
                .innerJoin(movieEntity).on(movieEntity.eq(scheduleEntity.movie))
                .where(
                        theaterEntity.id.eq(theaterId)
                )
                .transform(groupBy(screenEntity).as(new QSchedulePayload_Get(
                        scheduleEntity.id,
                        new QTheaterPayload_Get(theaterEntity.id, theaterEntity.name),
                        new QScreenPayload_Get(screenEntity.id, screenEntity.name, screenEntity.theaterId),
                        new QMoviePayload_Get(
                                movieEntity.id, movieEntity.title, movieEntity.filmRating,
                                movieEntity.genre, movieEntity.releasedAt, movieEntity.thumbnailUrl,
                                movieEntity.runningTime
                        ),
                        list(new QTimeTablePayload_Get(
                                scheduleEntity.startTime, scheduleEntity.endTime
                        ))
                )));
        return result.values().stream().toList();
    }

    public BooleanBuilder searchConditions(ScheduleStatement.Search search) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(getMovieNameCondition(search)).and(getMovieGenreCondition(search));
        return builder;
    }

    public BooleanExpression getMovieNameCondition(ScheduleStatement.Search search) {
        if (!StringUtils.hasText(search.title())) return null;
        return movieEntity.title.startsWith(search.title());
    }

    public BooleanExpression getMovieGenreCondition(ScheduleStatement.Search search) {
        if (search.genre() == null) return null;
        return movieEntity.genre.eq(search.genre());
    }

}
