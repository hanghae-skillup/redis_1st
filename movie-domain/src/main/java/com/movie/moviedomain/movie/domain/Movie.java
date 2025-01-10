package com.movie.moviedomain.movie.domain;

import com.movie.moviedomain.enums.FilmRating;
import com.movie.moviedomain.enums.Genre;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    private Long id;

    private String title;
    private LocalDateTime releasedAt;
    private String thumbnailUrl;
    private String runningTime;

    private FilmRating filmRating;
    private Genre genre;

    public Movie(Long id, String title,
                 LocalDateTime releasedAt, String thumbnailUrl,
                 String runningTime, FilmRating filmRating,
                 Genre genre
    ) {
        this.id = id;
        this.title = title;
        this.releasedAt = releasedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.runningTime = runningTime;
        this.filmRating = filmRating;
        this.genre = genre;
    }

    public static Movie of(Long id, String title,
                 LocalDateTime releasedAt, String thumbnailUrl,
                 String runningTime, FilmRating filmRating,
                 Genre genre
    ) {
        return new Movie(id, title, releasedAt, thumbnailUrl, runningTime, filmRating, genre);
    }
}
