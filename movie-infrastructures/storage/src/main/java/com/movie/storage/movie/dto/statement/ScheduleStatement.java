package com.movie.storage.movie.dto.statement;

import com.movie.common.enums.Genre;

public class ScheduleStatement {

    public record Search(String title, Genre genre) {
        public static Search of(String title, Genre genre) {
            return new Search(title, genre);
        }
    }

}
