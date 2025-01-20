package com.hanghae.application.projection;

import com.hanghae.domain.model.enums.MovieGenre;
import com.hanghae.domain.model.enums.MovieRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieScheduleProjection {
    private Long id;
    private String title;
    private MovieRating rating;
    private LocalDate releaseDate;
    private String thumbnail;
    private Long runningTimeMinutes;
    private MovieGenre genre;
    private String screenName;
    private LocalDateTime showStartAt;
}