package com.hh.infrastructure.movie;

import com.hh.domain.movie.FirmRating;
import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.MovieScreeningDto;
import com.hh.domain.movie.dto.ScreenDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static com.hh.domain.movie.QMovie.movie;
import static com.hh.domain.movie.QScreen.screen;
import static com.hh.domain.movie.QTheater.theater;
import static com.querydsl.core.types.dsl.Expressions.stringTemplate;

@Repository
@RequiredArgsConstructor
public class MovieScreeningRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public Page<MovieScreeningDto> getMovieScreenings(Pageable pageable, String reqTitle, Genre reqGenre, LocalDateTime today) {
    LocalDateTime startOfToday = LocalDate.now().atTime(LocalTime.MIN);
    LocalDateTime endOfToday = LocalDate.now().atTime(LocalTime.MAX);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Total count 계산 (페이징을 위한 전체 데이터 개수)
    long total = jpaQueryFactory
            .select(movie.id.countDistinct())
            .from(movie)
            .innerJoin(screen).on(movie.id.eq(screen.movieId))
            .where(
                    movie.releasedDate.loe(today) // 개봉일이 today 이전
                            .and(screen.startTime.goe(startOfToday))
                            .and(screen.startTime.loe(endOfToday))
                            .and(reqTitle != null && !reqTitle.isEmpty() ? movie.title.containsIgnoreCase(reqTitle) : null)
                            .and(reqGenre != null ? movie.genre.eq(reqGenre) : null)
            )
            .fetchOne();

    List<Tuple> results = jpaQueryFactory
            .select(
                    movie.id,
                    movie.title,
                    movie.firmRating,
                    movie.releasedDate,
                    movie.thumbnail,
                    movie.runningTime,
                    movie.genre,
                    stringTemplate("GROUP_CONCAT({0})", screen.name).as("screenNames"),
                    stringTemplate("GROUP_CONCAT(DATE_FORMAT({0}, '%H:%i'))", screen.startTime).as("startTimes"),
                    stringTemplate("GROUP_CONCAT(DATE_FORMAT({0}, '%H:%i'))", screen.endTime).as("endTimes")
            )
            .from(movie)
            .innerJoin(screen).on(movie.id.eq(screen.movieId))
            .innerJoin(theater).on(theater.id.eq(screen.theaterId))
            .where(
                    movie.releasedDate.loe(today) // 개봉일이 today 이전
                            .and( screen.startTime.goe(startOfToday))
                            .and(screen.startTime.loe(endOfToday))
                            .and(reqTitle != null && !reqTitle.isEmpty() ? movie.title.containsIgnoreCase(reqTitle) : null)
                            .and(reqGenre != null ? movie.genre.eq(reqGenre) : null)
            )
            .groupBy(movie.id)
            .orderBy(movie.id.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    // 결과 매핑
    List<MovieScreeningDto> mappedResults =  results.stream().map(tuple -> {
      int movieId = tuple.get(movie.id);
      String title = tuple.get(movie.title);
      FirmRating firmRating = tuple.get(movie.firmRating);
      LocalDateTime releasedDate = tuple.get(movie.releasedDate);
      String thumbnail = tuple.get(movie.thumbnail);
      Integer runningTime = tuple.get(movie.runningTime);
      Genre genre = tuple.get(movie.genre);

      String[] screenNames = tuple.get(Expressions.stringPath("screenNames")).split(",");
      String[] startTimes = tuple.get(Expressions.stringPath("startTimes")).split(",");
      String[] endTimes = tuple.get(Expressions.stringPath("endTimes")).split(",");

      List<ScreenDto> screens = new ArrayList<>();
      for (int i = 0; i < screenNames.length; i++) {
        screens.add(new ScreenDto(
                screenNames[i],
                startTimes[i],
                endTimes[i]
        ));
      }

      //startTime(상영 시작 시간) 빠른 시간 기준으로 정렬
      screens.sort(Comparator.comparing(ScreenDto::getStartTime));

      return new MovieScreeningDto(movieId, title, firmRating, releasedDate.format(formatter), thumbnail, runningTime, genre, screens);
    }).collect(Collectors.toList());

    return new PageImpl<>(mappedResults, pageable, total);
  }

}
