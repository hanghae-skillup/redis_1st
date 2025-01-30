package com.example.movie.response;

import com.example.repository.movie.dto.MovieDto;
import com.example.repository.movie.dto.ScreeningInfoDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class MovieResponse implements Serializable {
    private String title;
    private String thumbnailUrl;
    private String genre;
    private String rating;
    private LocalDate releaseDate;
    private List<ScreeningInfoResponse> screeningInfo;

    MovieResponse(MovieDto movie) {
        this.title = movie.getTitle();
        this.thumbnailUrl = movie.getThumbnailUrl();
        this.genre = movie.getGenre().getDescription();
        this.rating = movie.getRating().getDescription();
        this.releaseDate = movie.getReleaseDate();
        this.screeningInfo = createScreeningInfo(movie.getScreeningInfo());
    }

    private static List<ScreeningInfoResponse> createScreeningInfo(List<ScreeningInfoDto> screenings) {
        return screenings.stream()
                .sorted(Comparator.comparing(ScreeningInfoDto::getStartedAt))
                .map(screening ->
                        new ScreeningInfoResponse(
                                screening.getScreeningId(),
                                screening.getTheaterName(),
                                screening.getStartedAt().format(DateTimeFormatter.ofPattern("HH:mm")),
                                screening.getEndedAt().format(DateTimeFormatter.ofPattern("HH:mm"))))
                .collect(Collectors.toList());
    }
}
