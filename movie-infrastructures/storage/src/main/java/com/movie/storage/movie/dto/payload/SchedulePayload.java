package com.movie.storage.movie.dto.payload;

import com.movie.domain.movie.dto.info.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SchedulePayload {

    @Getter
    @NoArgsConstructor
    public static class Get {

        private Long id;
        private TheaterPayload.Get theater;
        private ScreenPayload.Get screen;
        private MoviePayload.Get movie;

        private List<TimeTablePayload.Get> timeTables;

        @QueryProjection
        public Get(Long id, TheaterPayload.Get theater,
                   ScreenPayload.Get screen, MoviePayload.Get movie,
                   List<TimeTablePayload.Get> timeTables
        ) {
            this.id = id;
            this.theater = theater;
            this.screen = screen;
            this.movie = movie;
            this.timeTables = timeTables;
        }

        public ScheduleInfo.Get to() {
            return ScheduleInfo.Get.of(
                    id, TheaterInfo.Get.of(theater.getId(), theater.getName()),
                    ScreenInfo.Get.of(screen.getId(), screen.getTheaterId(), screen.getName()),
                    MovieInfo.Get.of(
                            movie.getId(), movie.getTitle(), movie.getReleasedAt(),
                            movie.getThumbnailUrl(), movie.getRunningTime(),
                            movie.getFilmRating(), movie.getGenre()
                    ),
                    timeTables.stream()
                            .map(timeTable -> TimeTableInfo.Get.of(timeTable.getStartTime(), timeTable.getEndTime()))
                            .toList()
            );
        }

    }

}
