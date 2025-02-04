package com.movie.application.dto;

import com.movie.domain.entity.Movie;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieResponseDto {
    private Long id;
    private String title;
    private String genre;
    private int runningTime;
    private String description;
    
    public static MovieResponseDto from(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .runningTime(movie.getRunningTime())
                .description(movie.getDescription())
                .build();
    }
} 