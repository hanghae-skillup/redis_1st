package com.example.movie.application.service.movie.dto;

import com.example.movie.domain.movie.Movie;
import com.example.movie.domain.schedule.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record MovieResult(
    //영화 제목,
    String title,
    //영상물 등급,
    String ageRating,
    //개봉일,
    LocalDate releaseDate,
    //썸네일 이미지,
    String thumbnailUrl,
    //러닝 타임(분),
    Integer duration,
    //영화 장르,
    String category,
    //상영관 이름,
    String theaterName,
    //상영 시간표
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
        //상영 시간,
        LocalTime startTime,
        //종료 시간,
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
