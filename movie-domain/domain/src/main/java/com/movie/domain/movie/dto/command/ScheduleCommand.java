package com.movie.domain.movie.dto.command;

import com.movie.common.enums.Genre;

public class ScheduleCommand {

    public record Search(
            String title,
            Genre genre
    ) {
        public static Search of(String title, Genre genre) {
            return new Search(title, genre);
        }

    }

}
