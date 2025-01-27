package hellojpa.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.TimePath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hellojpa.domain.*;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;
import hellojpa.dto.TimeScheduleDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static hellojpa.domain.QMovie.*;
import static hellojpa.domain.QScreening.*;
import static hellojpa.domain.QTheater.*;

@RequiredArgsConstructor
public class ScreeningRepositoryImpl implements ScreeningRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScreeningDto> findCurrentScreeningsMovieInfo(LocalDate todayDate, SearchCondition searchCondition) {

        // 오늘 날짜를 기준으로 개봉한 영화만 조회
        BooleanExpression condition = movie.releaseDate.loe(todayDate);

        if (searchCondition.getTitle() != null && !searchCondition.getTitle().isEmpty()) {
            String titleSearch = searchCondition.getTitle().trim();
            //String titleSearch = searchCondition.getTitle().trim() + "%"; // like 사용
            condition = condition.and(movie.title.eq(titleSearch));
        }

        if (searchCondition.getGenre() != null && !searchCondition.getGenre().isEmpty()) {
            Genre genre = Genre.valueOf(searchCondition.getGenre());
            condition = condition.and(movie.genre.eq(genre));
        }

        return queryFactory
                .select(Projections.constructor(
                        ScreeningDto.class,
                        movie.title,
                        movie.rating,
                        movie.releaseDate,
                        movie.thumbnail,
                        movie.runningTime,
                        movie.genre
                ))
                .from(movie)
                .where(condition)
                .orderBy(movie.releaseDate.desc())
                .fetch();
    }

    // 영화에 해당하는 상영관과 상영시간 조회
    @Override
    public List<TheaterScheduleDto> findTheaterScheduleDtoByMovieTitles(List<String> titles) {
        return queryFactory
                .select(Projections.constructor(
                        TheaterScheduleDto.class,
                        screening.movie.title,
                        theater.name,
                        Projections.list(
                                Projections.constructor(
                                        TimeScheduleDto.class,
                                        screening.startTime,
                                        screening.movie.runningTime
                                )
                        )
                ))
                .from(screening)
                .join(screening.theater, theater)
                .join(screening.movie, movie)
                .where(screening.movie.title.in(titles))
                .orderBy(screening.startTime.asc())
                .fetch();
    }
}