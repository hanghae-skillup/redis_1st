package com.example.movie.application.service.movie.dto;

import com.example.movie.domain.movie.Movie;
import com.example.movie.domain.schedule.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Schema(title = "영화 객체")
public record MovieResult(
    @Schema(title = "영화 제목")
    String title,
    @Schema(title = "영상물 등급")
    String ageRating,
    @Schema(title = "개봉일")
    LocalDate releaseDate,
    @Schema(title = "썸네일 이미지")
    String thumbnailUrl,
    @Schema(title = "러닝 타임(분)")
    Integer duration,
    @Schema(title = "영화 장르")
    String category,
    @Schema(title = "상영관 이름")
    String theaterName,
    @Schema(title = "상영 시간표")
    List<MovieScheduleResult> schedules
) {

    public static MovieResult of(
        Movie movie
    ) {
        return new MovieResult(
            movie.title(),
            movie.ageRating().name(),
            movie.releaseDate(),
            movie.getThumbnailUrl(),
            movie.duration(),
            movie.category().name(),
            movie.theater().name(),
            MovieScheduleResult.of(movie.schedules())
        );
    }

    public record MovieScheduleResult(
        @Schema(title = "상영 시간")
        LocalTime startTime,
        @Schema(title = "종료 시간")
        LocalTime endTime
    ) {

        public static List<MovieScheduleResult> of(
            List<Schedule> schedule
        ) {
            return schedule.stream().map(MovieScheduleResult::of).collect(Collectors.toList());
        }

        public static MovieScheduleResult of(
            Schedule schedule
        ) {
            return new MovieScheduleResult(
                schedule.startAt(),
                schedule.endAt()
            );
        }
    }
}
