package com.movie.domain.movie.dto.command;

import com.movie.common.enums.Genre;

public class ScheduleCommand {

    public record Search(
            String movieName,
            Genre genre
    ) {
        public static Search of(String movieName, Genre genre) {
            return new Search(movieName, genre);
        }

    }

}
