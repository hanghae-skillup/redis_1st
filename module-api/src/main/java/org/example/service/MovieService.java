package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.movie.Movie;
import org.example.dto.ScreenInfoProjection;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.dto.response.ScreenInfo;
import org.example.dto.response.ScreenTimeInfo;
import org.example.repository.MovieJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieJpaRepository movieJpaRepository;

    public List<PlayingMoviesResponseDto> getPlayingMovies() {
        List<Movie> allPlayingMovies = movieJpaRepository.findAllPlayingMovies();

        return allPlayingMovies.stream().map(movie -> {
            List<ScreenInfoProjection> screenInfoProjections = movieJpaRepository.findScreenInfos(movie.getId());
            Map<String, List<ScreenTimeInfo>> groupedByRoom = new HashMap<>();

            for (ScreenInfoProjection screenInfoProjection : screenInfoProjections) {
                ScreenTimeInfo screenTimeInfo = new ScreenTimeInfo(screenInfoProjection.getStartTime(), screenInfoProjection.getEndTime());

                groupedByRoom
                        .computeIfAbsent(screenInfoProjection.getScreenRoom(), key -> new ArrayList<>())
                        .add(screenTimeInfo);
            }

            List<ScreenInfo> screenInfos = groupedByRoom.entrySet().stream()
                    .map(entry -> new ScreenInfo(entry.getKey(), entry.getValue()))
                    .toList();

            return new PlayingMoviesResponseDto(
                    movie.getTitle(),
                    movie.getThumbnail(),
                    movie.getGenre(),
                    movie.getAgeRating(),
                    movie.getReleaseDate(),
                    movie.getRunningTime(),
                    screenInfos
            );
        }).collect(Collectors.toList());
    }
}