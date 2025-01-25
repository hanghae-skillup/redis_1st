package com.example.movie.service;

import com.example.movie.converter.DtoConverter;
import com.example.movie.dto.MoviesDetail;
import com.example.jpa.entity.movie.Genre;
import com.example.jpa.repository.movie.MovieRepository;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;
import com.example.movie.dto.ScreeningTimeDetail;
import com.example.movie.dto.ScreeningsDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieCacheService movieCacheService;
    private final DtoConverter dtoConverter;

    public List<MoviesDetail> getMovies(LocalDateTime now, Boolean isNowShowing, Genre genre, String search) {
        List<MoviesDetailDto> cachedMovies = movieCacheService.getMoviesByGenre(genre);
        List<MoviesDetail> moviesDetails = dtoConverter.moviesNowScreening(cachedMovies);
        return filterByStartAtAndTitle(now, isNowShowing, search, moviesDetails);
    }

    private static List<MoviesDetail> filterByStartAtAndTitle(LocalDateTime now, Boolean isNowShowing, String search, List<MoviesDetail> cachedMovies) {
        return cachedMovies.stream()
                .map(movie -> {
                    List<ScreeningsDetail> filteredScreenings = movie.screeningsDetails().stream()
                            .map(screening -> {
                                List<ScreeningTimeDetail> filteredTimes = screening.screeningTimes().stream()
                                        .filter(time -> isNowShowing == null || !isNowShowing || time.startAt().isAfter(now))
                                        .toList();
                                return new ScreeningsDetail(screening.theaterId(), screening.theater(), filteredTimes);
                            })
                            .filter(screening -> !screening.screeningTimes().isEmpty())
                            .toList();

                    return new MoviesDetail(
                            movie.movieId(),
                            movie.movieName(),
                            movie.grade(),
                            movie.releaseDate(),
                            movie.thumbnail(),
                            movie.runningTime(),
                            movie.genre(),
                            filteredScreenings
                    );
                })
                .filter(movie -> {
                    boolean matchesSearch = search == null || movie.movieName().toLowerCase().contains(search.toLowerCase());
                    return !movie.screeningsDetails().isEmpty() && matchesSearch;
                })
                .sorted(Comparator.comparing(MoviesDetail::releaseDate).reversed())
                .toList();
    }

}
