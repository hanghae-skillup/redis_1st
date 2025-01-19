package com.example.repository.movie;

import com.example.entity.Genre;
import com.example.entity.Rating;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieDto {
    private final Long id;
    private final String title;
    private final String thumbnailUrl;
    private final Genre genre;
    private final Rating rating;
    private final LocalDate releaseDate;
    private List<ScreeningInfoDto> screeningInfo;

    @QueryProjection
    public MovieDto(Long id, String title, String thumbnailUrl, Genre genre, Rating rating, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.genre = genre;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public void addScreeningInfo(List<ScreeningInfoDto> screeningInfo) {
        this.screeningInfo = screeningInfo;
    }
}
