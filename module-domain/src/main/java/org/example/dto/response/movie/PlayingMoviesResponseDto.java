package org.example.dto.response.movie;

import lombok.AllArgsConstructor;
import org.example.domain.movie.AgeRating;
import org.example.domain.movie.Genre;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class PlayingMoviesResponseDto {
    private String title;
    private String thumbnail;
    private Genre genre;
    private AgeRating ageRating;
    private LocalDate releaseDate;
    private int runningTime;
    private List<ScreenScheduleDto> screenSchedules;
}
