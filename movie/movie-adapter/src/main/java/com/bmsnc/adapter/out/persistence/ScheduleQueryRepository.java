package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.querydsl.model.MovieQueryModel;
import com.bmsnc.adapter.out.querydsl.model.QMovieQueryModel;
import com.bmsnc.applicaion.port.in.RunningMovieCommand;

import com.bmsnc.common.dto.MovieGenre;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.bmsnc.adapter.out.persistence.QMovie.*;
import static com.bmsnc.adapter.out.persistence.QMovieTheaterInfo.*;
import static com.bmsnc.adapter.out.persistence.QSchedule.*;
import static com.bmsnc.adapter.out.persistence.QTheater.theater;

@Repository
@RequiredArgsConstructor
public class ScheduleQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public List<MovieQueryModel> searchRunningMovies(RunningMovieCommand command) {

        return jpaQueryFactory
                .select(new QMovieQueryModel(
                        movie.movieId,
                        movie.movieName,
                        movie.movieGrade,
                        movie.movieReleaseAt,
                        movie.movieImageUrl,
                        movie.runningTimeMinutes,
                        movie.movieGenre,
                        theater.theaterName,
                        schedule.movieStartAt
                ))
                .from(movieTheaterInfo)
                .leftJoin(movie)
                    .on(movieTheaterInfo.movie.movieId.eq(movie.movieId))
                .leftJoin(theater)
                    .on(movieTheaterInfo.theater.theaterId.eq(theater.theaterId))
                .innerJoin(schedule)
                    .on(movieTheaterInfo.movieTheaterInfoId.eq(schedule.movieTheaterInfo.movieTheaterInfoId))
                .where(
                        theater.theaterId.eq(command.getTheaterId()),
                        isScreening(),
                        likeMovieName(command.getMovieName()),
                        eqMovieGenre(command.getMovieGenre())
                )
                .orderBy(movie.movieReleaseAt.asc(), schedule.movieStartAt.asc())
                .fetch();
    }

    BooleanExpression likeMovieName(String movieName) {
        return StringUtils.hasText(movieName) ? movie.movieName.contains(movieName) : null;
    }

    BooleanExpression eqMovieGenre(MovieGenre movieGenre) {
        return !StringUtils.hasText(movieGenre.toString()) ? null
                : (MovieGenre.ETC.equals(movieGenre) ? null
                : movie.movieGenre.eq(movieGenre));

    }

    public BooleanExpression isScreening() {
        LocalDate today = LocalDate.now();
        return Expressions.asDate(today)
                .between(
                        Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, {1})", schedule.screenOpenAt, "%Y-%m-%d"),
                        Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, {1})", schedule.screenCloseAt, "%Y-%m-%d")
                );
    }
}
