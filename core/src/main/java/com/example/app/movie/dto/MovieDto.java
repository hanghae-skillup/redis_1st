package com.example.app.movie.dto;

import com.example.app.movie.entity.Movie;
import com.example.app.movie.entity.Showtime;
import com.example.app.movie.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String description;
    private String rating;
    private String genre;
    private String thumbnail;
    private int runningTime;
    private LocalDate releaseDate;
    private List<String> theaters;
    private List<String> showtimes;

    public static MovieDto toDto(Movie movie) {
        var showtimes = movie.getShowtimes()
                .stream()
                .sorted(Comparator.comparing(Showtime::getStart))
                .map(showtime -> String.format("%s ~ %s", showtime.getStart(), showtime.getEnd()))
                .toList();

        var theaters = movie.getTheaters()
                .stream()
                .map(Theater::getName)
                .toList();

        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating().getDescription())
                .genre(movie.getGenre().getDescription())
                .thumbnail(movie.getThumbnail())
                .runningTime(movie.getRunningTime())
                .releaseDate(movie.getReleaseDate())
                .theaters(theaters)
                .showtimes(showtimes)
                .build();
    }
}
