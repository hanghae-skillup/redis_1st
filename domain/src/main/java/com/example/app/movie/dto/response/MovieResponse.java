package com.example.app.movie.dto.response;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.domain.Showtime;
import com.example.app.movie.domain.Theater;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        String status,
        String rating,
        String genre,
        String thumbnail,
        int runningTime,
        LocalDate releaseDate,
        List<String> showtimes,
        List<String> theaters
){
    public static MovieResponse toResponse(Movie movie) {
        var showtimes = movie.showtimes()
                .stream()
                .sorted(Comparator.comparing(Showtime::start))
                .map(showtime -> String.format("%s ~ %s",
                        showtime.start().format(DateTimeFormatter.ofPattern("HH:mm")),
                        showtime.end().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .toList();

        var theaters = movie.theaters()
                .stream()
                .map(Theater::name)
                .toList();

        return MovieResponse.builder()
                .id(movie.id())
                .title(movie.title())
                .description(movie.description())
                .status(movie.status().getDescription())
                .rating(movie.rating().getDescription())
                .genre(movie.genre().getDescription())
                .thumbnail(movie.thumbnail())
                .runningTime(movie.runningTime())
                .releaseDate(movie.releaseDate())
                .showtimes(showtimes)
                .theaters(theaters)
                .build();
    }
}
