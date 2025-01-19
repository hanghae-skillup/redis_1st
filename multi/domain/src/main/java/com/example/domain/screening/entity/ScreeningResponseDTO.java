package com.example.domain.screening.entity;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
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

    @QueryProjection
    public ScreeningResponseDTO(String movieName, String title, String rating, LocalDateTime releaseDate,
                                String thumbnailUrl, Integer runningTime, String genre, String theaterName,
                                LocalDateTime startTime, LocalDateTime endTime, LocalDateTime screeningDate) {
        this.movieName = movieName;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.thumbnailUrl = thumbnailUrl;
        this.runningTime = runningTime;
        this.genre = genre;
        this.theaterName = theaterName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screeningDate = screeningDate;
    }

}

