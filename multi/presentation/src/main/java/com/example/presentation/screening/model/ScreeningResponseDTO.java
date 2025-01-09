package com.example.presentation.screening.model;

import com.example.domain.screening.Screening;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreeningResponseDTO {

    private String movieName;

    private String title;

    private String rating;

    private LocalDateTime releaseDate;

    private String thumbnailUrl;

    private Integer runningTime;

    private String genre;

    private String theaterName;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private LocalDateTime screeningDate;


    public static ScreeningResponseDTO of(Screening screening){
        var movie = screening.getMovie();
        var theater = screening.getTheater();
        return ScreeningResponseDTO.builder()
                .movieName(movie.getTitle())
                .title(movie.getTitle())
                .rating(movie.getRating().toString())
                .releaseDate(movie.getReleaseDate())
                .thumbnailUrl(movie.getThumbnailUrl())
                .runningTime(movie.getRunningTime())
                .genre(movie.getGenre().toString())
                .theaterName(theater.getTheaterName())
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .screeningDate(screening.getScreeningDate())
                .build();
    }

}

