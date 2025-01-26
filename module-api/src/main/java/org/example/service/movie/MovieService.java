package org.example.service.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MovieScreeningInfo;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.dto.response.ScreeningInfo;
import org.example.dto.response.ScreeningTimeInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final FindMovieService findMovieService;

    public List<PlayingMoviesResponseDto> getPlayingMovies(MoviesFilterRequestDto moviesFilterRequestDto) {
        List<MovieScreeningInfo> movieScreeningInfos = findMovieService.getPlayingMovies(moviesFilterRequestDto).getMovieScreeningInfos();

        Map<Long, PlayingMoviesResponseDto> movieInfoMap = new HashMap<>();

        for (MovieScreeningInfo movieScreeningInfo : movieScreeningInfos) {
            PlayingMoviesResponseDto playingMoviesResponseDto = getPlayingMoviesResponseDto(movieScreeningInfo, movieInfoMap, movieScreeningInfo.getMovieId());
            ScreeningInfo screeningInfo = getScreeningInfo(movieScreeningInfo, playingMoviesResponseDto);
            screeningInfo.getScreeningTimeInfos().add(
                    new ScreeningTimeInfo(movieScreeningInfo.getStartTime(), movieScreeningInfo.getEndTIme())
            );
        }

        return new ArrayList<>(movieInfoMap.values());
    }

    private PlayingMoviesResponseDto getPlayingMoviesResponseDto(MovieScreeningInfo movieScreeningInfo, Map<Long, PlayingMoviesResponseDto> movieInfoMap, Long movieId) {
        return movieInfoMap.computeIfAbsent(movieId, t -> new PlayingMoviesResponseDto(
                movieScreeningInfo.getTitle(),
                movieScreeningInfo.getThumbnail(),
                movieScreeningInfo.getGenre(),
                movieScreeningInfo.getAgeRating(),
                movieScreeningInfo.getReleaseDate(),
                movieScreeningInfo.getRunningTime(),
                new ArrayList<>()
        ));
    }

    private ScreeningInfo getScreeningInfo(MovieScreeningInfo movieScreeningInfo, PlayingMoviesResponseDto playingMoviesResponseDto) {
        return playingMoviesResponseDto.getScreeningInfos().stream()
                .filter(i -> i.getScreenRoomName().equals(movieScreeningInfo.getScreenRoomName()))
                .findFirst()
                .orElseGet(() -> {
                    ScreeningInfo newInfo = new ScreeningInfo(movieScreeningInfo.getScreenRoomName(), new ArrayList<>());
                    playingMoviesResponseDto.getScreeningInfos().add(newInfo);
                    return newInfo;
                });
    }
}