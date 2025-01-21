package com.movie.storage.movie.dto.payload;

import com.movie.common.enums.FilmRating;
import com.movie.common.enums.Genre;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MoviePayload {

    @Getter
    @NoArgsConstructor
    public static class Get {

        private Long id;
        private String title;
        private FilmRating filmRating;
        private Genre genre;
        private LocalDateTime releasedAt;
        private String thumbnailUrl;
        private String runningTime;

        @QueryProjection
        public Get(Long id, String title, FilmRating filmRating, Genre genre, LocalDateTime releasedAt, String thumbnailUrl, String runningTime) {
            this.id = id;
            this.title = title;
            this.filmRating = filmRating;
            this.genre = genre;
            this.releasedAt = releasedAt;
            this.thumbnailUrl = thumbnailUrl;
            this.runningTime = runningTime;
        }
    }

}
