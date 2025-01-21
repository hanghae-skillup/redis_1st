package org.haas.application.service;

import lombok.RequiredArgsConstructor;
import org.haas.core.domain.movie.Movie;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ScreeningService {

    public void validateScreeningDate(Movie movie, LocalDateTime screeningDateTime) {
        if (!movie.isValidScreeningDate(screeningDateTime.toLocalDate())) {
            throw new IllegalArgumentException(
                    "상영 날짜는 영화 개봉일 이후여야 합니다."
            );
        }
    }
}
