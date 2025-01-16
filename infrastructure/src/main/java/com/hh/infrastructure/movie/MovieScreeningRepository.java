package com.hh.infrastructure.movie;

import com.hh.domain.movie.FirmRating;
import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.MovieScreeningDto;
import com.hh.domain.movie.dto.ScreenDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
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

/*  public List<MovieScreeningDto> findMovieScreening(){
    return jpaQueryFactory.select(Projections.constructor(MovieScreeningDto.class,
              movie.title,
            movie.firmRating,
            theater.name,
            movie.thumbnail,
            movie.runningTime,
            movie.genre,
            screen.startTime,
            screen.endTime
            ))
            .from(movie)
            .join(screen).on(movie.id.eq(screen.movieId))
            .join(theater).on(theater.id.eq(screen.theaterId))
            .fetch();
  }*/

  public List<MovieScreeningDto> findMoviesWithGroupConcat(Pageable pageable, String searchKeyword, LocalDateTime today) {
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
                    stringTemplate("GROUP_CONCAT({0})", screen.startTime).as("startTimes"),
                    stringTemplate("GROUP_CONCAT({0})", screen.endTime).as("endTimes")
            )
            .from(movie)
            .innerJoin(screen).on(movie.id.eq(screen.movieId))
            .innerJoin(theater).on(theater.id.eq(screen.theaterId))
            .where(
                    movie.releasedDate.loe(today) // 개봉일이 today 이전
                            .and(searchKeyword != null && !searchKeyword.isEmpty()
                                    ? movie.title.containsIgnoreCase(searchKeyword)
                                    : null)
            )
            .groupBy(movie.id)
            .orderBy(movie.id.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    for (Tuple tuple : results) {
      // 디버깅: Tuple 전체 내용 확인
      System.out.println(tuple);

      // GROUP_CONCAT 결과 가져오기
      String screenNames = tuple.get(Expressions.stringPath("screenNames")); // 별칭 사용
      System.out.println("Screen Names: " + screenNames);
    }
   // return null;
    // 결과 매핑
    return results.stream().map(tuple -> {
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
                startTimes[i].substring(11, 16),
                endTimes[i].substring(11, 16)
        ));
      }

      //startTime 기준으로 정렬
      screens.sort(Comparator.comparing(ScreenDto::getStartTime));

      return new MovieScreeningDto(movieId, title, firmRating, releasedDate, thumbnail, runningTime, genre, screens);
    }).collect(Collectors.toList());
  }

}
