package com.example.response;

import com.example.entity.Movie;
import com.example.entity.MovieTheater;
import com.example.entity.Screening;
import com.example.entity.Theater;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MovieResponse {
    private String title;
    private String thumbnailUrl;
    private String genre;
    private String rating;
    private LocalDate releaseDate;
    private List<String> theaters;
    private List<String> screenings;

    MovieResponse(Movie movie) {
        this.title = movie.getTitle();
        this.thumbnailUrl = movie.getThumbnailUrl();
        this.genre = movie.getGenre().getDescription();
        this.rating = movie.getRating().getDescription();
        this.releaseDate = movie.getReleaseDate();
        this.theaters = createTheaters(movie);
        this.screenings = createScreening(movie);
    }

    private static List<String> createTheaters(Movie movie) {
        return movie.getMovieTheaters().stream()
                .map(MovieTheater::getTheater)
                .map(Theater::getName)
                .collect(Collectors.toList());
    }

    private static List<String> createScreening(Movie movie) {
        return movie.getScreenings().stream()
                .sorted(Comparator.comparing(Screening::getStartedAt))
                .map(screening -> screening.getStartedAt().format(DateTimeFormatter.ofPattern("HH:mm"))
                        + " ~ "
                        + screening.getEndedAt().format(DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }
}
