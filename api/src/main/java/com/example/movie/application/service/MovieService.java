package com.example.movie.application.service;

import com.example.movie.application.convertor.DtoConvertor;
import com.example.movie.application.dto.MoviesDetail;
import com.example.movie.entity.movie.Genre;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.dto.MoviesDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final DtoConvertor dtoConvertor;

    @Cacheable(
            cacheNames = "getMovies",
            key = "'movies:genre:' + #genre + ':search:' + #search",
            cacheManager = "cacheManager"
    )
    public List<MoviesDetail> getMovies(LocalDateTime now, Boolean isNowShowing, Genre genre, String search) {
        if (isNowShowing) {
            return getMoviesNowShowing(now, genre, search);
        }
        return getMoviesAll(genre,search);
    }

    public List<MoviesDetail> getMoviesNowShowing(LocalDateTime now, Genre genre, String search) {
        System.out.println(now);
        List<MoviesDetailDto> dbResults = movieRepository.findAll(now,genre,search);
        System.out.println(dbResults.size());
        return convertMovie(dbResults);
    }

    public List<MoviesDetail> getMoviesAll(Genre genre, String search) {
        List<MoviesDetailDto> dbResults = movieRepository.findAll(null,genre,search);
        return convertMovie(dbResults);
    }

    private List<MoviesDetail> convertMovie(List<MoviesDetailDto> dbResults){
        List<MoviesDetail> detailsList = dtoConvertor.moviesNowScreening(dbResults);

        return detailsList.stream()
                .sorted(Comparator.comparing(MoviesDetail::releaseDate).reversed())
                .toList();
    }

}
