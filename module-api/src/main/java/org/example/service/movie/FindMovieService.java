package org.example.service.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.movie.Genre;
import org.example.dto.MovieScreeningInfo;
import org.example.dto.request.MoviesFilterRequestDto;
import org.example.dto.response.FoundMovieScreeningInfoList;
import org.example.repository.MovieJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindMovieService {
    private final MovieJpaRepository movieJpaRepository;

    @Cacheable(value = "playingMovies",
            key = "#moviesFilterRequestDto.genre + #moviesFilterRequestDto.playing")
    public FoundMovieScreeningInfoList getPlayingMovies(MoviesFilterRequestDto moviesFilterRequestDto) {
        List<MovieScreeningInfo> movieScreeningInfos = movieJpaRepository.findScreeningInfos(moviesFilterRequestDto.getMovieTitle(), Genre.valueOf(moviesFilterRequestDto.getGenre()), moviesFilterRequestDto.isPlaying())
                .stream()
                .filter(movieScreeningInfo -> movieScreeningInfo.getTitle().contains(moviesFilterRequestDto.getMovieTitle()))
                .toList();

        return new FoundMovieScreeningInfoList(movieScreeningInfos);
    }
}
