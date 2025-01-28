package org.haas.adapter.mapper;

import org.haas.adapter.in.dto.MovieResponseDto;
import org.haas.adapter.in.dto.ScreeningResponseDto;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.screening.Screening;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class MovieMapperAdapter {

    public MovieResponseDto toDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .runningTime(movie.getRunningTime())
                .director(movie.getDirector())
                .filmRating(movie.getFilmRating())
                .releasedAt(movie.getReleasedAt())
                .thumbnailUrl(movie.getThumbnailUrl())
                .movieStatus(movie.getMovieStatus())
                .screenings(mapScreenings(movie.getScreenings()))
                .build();
    }

    private List<ScreeningResponseDto> mapScreenings(List<Screening> screenings) {
        return screenings.stream()
                .map(this::toScreeningDto)
                .collect(toList());
    }


    private ScreeningResponseDto toScreeningDto(Screening screening) {
        return ScreeningResponseDto.builder()
                .screeningId(screening.getScreeningId())
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
                .build();
    }


}
