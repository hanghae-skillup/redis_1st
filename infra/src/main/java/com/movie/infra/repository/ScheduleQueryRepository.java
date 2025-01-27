package com.movie.infra.repository;

import com.movie.domain.entity.QSchedule;
import com.movie.domain.entity.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Schedule> findAllAfterCurrentTime() {
        QSchedule schedule = QSchedule.schedule;

        return queryFactory
                .selectFrom(schedule)
                .where(schedule.startAt.after(LocalDateTime.now()))
                .fetch();
    }
} 