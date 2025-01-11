package com.movie.app.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class MovieRequestDto {
    private String title;
    private String rating;
    private String releaseDate;
    private String thumbnailImage;
    private String runningTime;
    private String genre;
    private String theater;
    private List<String> screeningSchedule;
}
