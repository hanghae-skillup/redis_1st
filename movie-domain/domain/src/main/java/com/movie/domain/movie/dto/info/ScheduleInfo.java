package com.movie.domain.movie.dto.info;

import java.util.List;

public class ScheduleInfo {

    public record Get(
            Long id, TheaterInfo.Get theater,
            ScreenInfo.Get screen, MovieInfo.Get movie,
            List<TimeTableInfo.Get> timeTables
    ) {
        public static Get of(Long id, TheaterInfo.Get theater,
                             ScreenInfo.Get screen, MovieInfo.Get movie,
                             List<TimeTableInfo.Get> timeTables) {
            return new Get(id, theater, screen, movie, timeTables);
        }

        public static Get of(Long id, TheaterInfo.Get theater,
                             MovieInfo.Get movie,
                             List<TimeTableInfo.Get> timeTables) {
            return new Get(id, theater, null, movie, timeTables);
        }

        public static Get of(Long id) {
            return new Get(id, null, null, null, null);
        }

        public static Get of(Long id, MovieInfo.Get movie) {
            return new Get(id, null, null, movie, null);
        }
    }

}
