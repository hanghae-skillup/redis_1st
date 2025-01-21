package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.movie.AgeRating;
import org.example.domain.movie.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MovieScreeningInfo {
    private Long movieId;
    private String title;
    private String thumbnail;
    private Genre genre;
    private AgeRating ageRating;
    private LocalDate releaseDate;
    private int runningTime;
    private String screenRoomName;
    private LocalDateTime startTime;
    private LocalDateTime endTIme;
}
