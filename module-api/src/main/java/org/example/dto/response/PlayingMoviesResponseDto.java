package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.domain.movie.AgeRating;
import org.example.domain.movie.Genre;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class PlayingMoviesResponseDto {
    private String title;
    private String thumbnail;
    private Genre genre;
    private AgeRating ageRating;
    private LocalDate releaseDate;
    private int runningTime;
    private List<ScreenInfo> screenInfos;
}
