package org.haas.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;
import org.haas.core.domain.genre.Genre;
import org.haas.core.domain.movie.MovieStatus;
import org.haas.core.domain.screening.Screening;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class MovieResponseDto {

    private Long id;

    private String title;

    private Genre genre;

    private int runningTime;

    private String director;

    private String filmRating;

    private LocalDate releasedAt;

    private String thumbnailUrl;

    private MovieStatus movieStatus;

    private List<ScreeningResponseDto> screenings;

}