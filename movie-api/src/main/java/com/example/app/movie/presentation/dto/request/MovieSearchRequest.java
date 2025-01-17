package com.example.app.movie.presentation.dto.request;

import com.example.app.common.annotation.ValidEnum;
import com.example.app.movie.dto.MovieSearchCommand;
import com.example.app.movie.type.MovieGenre;
import org.hibernate.validator.constraints.Length;

public record MovieSearchRequest(
        @Length(max = 255, message = "제목은 255자 이하로 검색해주세요")
        String title,
        @ValidEnum(enumClass = MovieGenre.class)
        String genre
){
        public static MovieSearchCommand toMovieSearchCommand(MovieSearchRequest movieSearchRequest) {
                return MovieSearchCommand.builder()
                        .title(movieSearchRequest.title)
                        .genre(MovieGenre.valueOf(movieSearchRequest.genre))
                        .build();
        }
}