package com.example.application.dto.response;

import com.example.domain.model.entity.Movie;
import com.example.domain.model.projection.MovieProjection;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record MovieResponseDto(
        String title,
        String contentRating,
        LocalDate releaseDate,
        String thumbnailUrl,
        int runtimeMinutes,
        String genre,
        String theater,
        List<ScreeningResponseDto> screenings
) implements Serializable {

    public static MovieResponseDto fromProjection(MovieProjection movie) {
        String theaterName = movie.getScreenings().stream()
                .findFirst()  // 첫 번째 screening 에서 가져옴
                .map(screening -> screening.getTheater().getName())  // theater 이름 추출
                .orElse("No Theater Available");  // theater 없는 경우

        return new MovieResponseDto(
                movie.getTitle(),
                movie.getContentRating(),
                movie.getReleaseDate(),
                movie.getThumbnailUrl(),
                movie.getRuntimeMinutes(),
                movie.getGenre(),
                theaterName,
                movie.getScreenings().stream()
                        .map(ScreeningResponseDto::fromProjection)
                        .collect(Collectors.toList())
        );
    }

    public static MovieResponseDto fromEntity(Movie movie) {
        String theaterName = movie.getScreenings().stream()
                .findFirst()  // 첫 번째 screening 에서 가져옴
                .map(screening -> screening.getTheater().getName())  // theater 이름 추출
                .orElse("No Theater Available");  // theater 없는 경우

        return new MovieResponseDto(
                movie.getTitle(),
                movie.getContentRating().name(),
                movie.getReleaseDate(),
                movie.getThumbnailUrl(),
                movie.getRuntimeMinutes(),
                movie.getGenre().name(),
                theaterName,
                movie.getScreenings().stream()
                        .map(ScreeningResponseDto::fromEntity)
                        .collect(Collectors.toList()) // Screening -> ScreeningResponseDto
        );
    }

}
