package com.movie.storage.movie.dto.payload;

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
    }

}
