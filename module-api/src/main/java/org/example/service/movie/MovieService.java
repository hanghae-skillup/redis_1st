package org.example.service.movie;

import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MovieScreeningInfo;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.PlayingMoviesResponseDto;
import org.example.dto.response.ScreeningInfo;
import org.example.dto.response.ScreeningTimeInfo;
import org.example.exception.RateLimitExceededException;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.example.baseresponse.BaseResponseStatus.TOO_MANY_REQUEST_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final FindMovieService findMovieService;

    // 초당 2개의 요청을 허용
    public final RateLimiter rateLimiter;

    public List<PlayingMoviesResponseDto> getPlayingMovies(MoviesFilterRequestDto moviesFilterRequestDto) {
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException(TOO_MANY_REQUEST_ERROR);
        }

        List<MovieScreeningInfo> movieScreeningInfos =
                findMovieService.getPlayingMovies(moviesFilterRequestDto).getMovieScreeningInfos()
                        .stream()
                        .filter(movieScreeningInfo ->
                                Optional.ofNullable(movieScreeningInfo.getTitle()).orElse("")
                                        .contains(Optional.ofNullable(moviesFilterRequestDto.getMovieTitle()).orElse("")))
                        .toList();

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