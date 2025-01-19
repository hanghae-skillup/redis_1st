package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.movie.Genre;
import org.example.dto.MovieScreeningInfo;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.dto.response.ScreeningInfo;
import org.example.dto.response.ScreeningTimeInfo;
import org.example.repository.MovieJpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieJpaRepository movieJpaRepository;

    public List<PlayingMoviesResponseDto> getPlayingMovies(MoviesFilterRequestDto moviesFilterRequestDto) {
        Genre genre = Arrays.stream(Genre.values())
                .filter(g -> g.name().equalsIgnoreCase(moviesFilterRequestDto.getGenre()))
                .findFirst()
                .orElse(null);
        List<MovieScreeningInfo> movieScreeningInfos =
                movieJpaRepository.findScreeningInfos(moviesFilterRequestDto.getMovieTitle(), genre, moviesFilterRequestDto.isPlaying());
        Map<Long, PlayingMoviesResponseDto> movieInfoMap = new HashMap<>();

        for (MovieScreeningInfo movieScreeningInfo : movieScreeningInfos) {
            Long movieId = movieScreeningInfo.getMovieId();

            PlayingMoviesResponseDto playingMoviesResponseDto = movieInfoMap.computeIfAbsent(movieId, t -> new PlayingMoviesResponseDto(
                    movieScreeningInfo.getTitle(),
                    movieScreeningInfo.getThumbnail(),
                    movieScreeningInfo.getGenre(),
                    movieScreeningInfo.getAgeRating(),
                    movieScreeningInfo.getReleaseDate(),
                    movieScreeningInfo.getRunningTime(),
                    new ArrayList<>()
            ));

            ScreeningInfo screeningInfo = playingMoviesResponseDto.getScreeningInfos().stream()
                    .filter(i -> i.getScreenRoomName().equals(movieScreeningInfo.getScreenRoomName()))
                    .findFirst()
                    .orElseGet(() -> {
                        ScreeningInfo newInfo = new ScreeningInfo(movieScreeningInfo.getScreenRoomName(), new ArrayList<>());
                        playingMoviesResponseDto.getScreeningInfos().add(newInfo);
                        return newInfo;
                    });

            screeningInfo.getScreeningTimeInfos().add(
                    new ScreeningTimeInfo(movieScreeningInfo.getStartTime(), movieScreeningInfo.getEndTIme())
            );
        }

        return new ArrayList<>(movieInfoMap.values());
    }
}