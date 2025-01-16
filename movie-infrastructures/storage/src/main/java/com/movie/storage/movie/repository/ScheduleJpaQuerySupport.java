package com.movie.storage.movie.repository;

import com.movie.moviedomain.movie.domain.Schedule;
import com.movie.storage.movie.dto.*;
import com.movie.storage.movie.dto.payload.SchedulePayload;
import com.movie.storage.movie.dto.statement.ScheduleStatement;
import com.movie.storage.movie.entity.ScheduleEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
        super(Schedule.class);
        this.queryFactory = queryFactory;
    }

    public List<SchedulePayload.Get> getSchedules(ScheduleStatement.Search search) {
        Map<ScheduleEntity, SchedulePayload.Get> result = queryFactory.selectFrom(scheduleEntity)
                .leftJoin(theaterEntity).on(theaterEntity.eq(scheduleEntity.theater))
                .leftJoin(screenEntity).on(screenEntity.eq(scheduleEntity.screen))
                .leftJoin(movieEntity).on(movieEntity.eq(scheduleEntity.movie))
                .where(
                        searchConditions(search)
                )
                .orderBy(movieEntity.releasedAt.asc())
                .transform(groupBy(scheduleEntity).as(new QSchedulePayload_Get(
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
        if (!StringUtils.hasText(search.movieName())) return null;
        return movieEntity.title.contains(search.movieName());
    }

    public BooleanExpression getMovieGenreCondition(ScheduleStatement.Search search) {
        if (search.genre() == null) return null;
        return movieEntity.genre.eq(search.genre());
    }

}
