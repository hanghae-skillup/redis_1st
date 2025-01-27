package com.example.app.movie.dto;

import com.example.app.movie.type.MovieGenre;
import lombok.Builder;

@Builder
public record SearchMovieCommand(
        String title,
        MovieGenre genre
) {
}
