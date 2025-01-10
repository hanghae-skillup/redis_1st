package com.movie.moviedomain.movie.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    private Long id;

    private Theater theater;
    private Screen screen;
    private Movie movie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule(Long id, Theater theater,
                    Screen screen, Movie movie,
                    LocalDateTime startTime, LocalDateTime endTime
    ) {
        this.id = id;
        this.theater = theater;
        this.screen = screen;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Schedule of(Long id, Theater theater,
                              Screen screen, Movie movie,
                              LocalDateTime startTime, LocalDateTime endTime
    ) {
        return new Schedule(id, theater, screen, movie, startTime, endTime);
    }

    public static Schedule of(Long id, Theater theater,
                              Movie movie,
                              LocalDateTime startTime, LocalDateTime endTime
    ) {
        return new Schedule(id, theater, null, movie, startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        return id != null && id.equals(schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
