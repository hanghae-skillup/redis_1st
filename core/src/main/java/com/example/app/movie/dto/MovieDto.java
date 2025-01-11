package com.example.app.movie.dto;

import com.example.app.movie.entity.Movie;
import com.example.app.movie.entity.Showtime;
import com.example.app.movie.entity.Theater;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Builder
public record MovieDto(
        Long id,
        String title,
        String description,
        String rating,
        String genre,
        String thumbnail,
        int runningTime,
        LocalDate releaseDate,
        List<String> theaters,
        List<String> showtimes
) {
    public static MovieDto toDto(Movie movie) {
        var showtimes = movie.getShowtimes()
                .stream()
                .sorted(Comparator.comparing(Showtime::getStart))
                .map(showtime -> String.format("%s ~ %s",
                        showtime.getStart().format(DateTimeFormatter.ofPattern("HH:mm")),
                        showtime.getEnd().format(DateTimeFormatter.ofPattern("HH:mm"))))
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
