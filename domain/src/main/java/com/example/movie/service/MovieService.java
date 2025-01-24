package com.example.movie.service;

import com.example.movie.converter.DtoConverter;
import com.example.movie.dto.MoviesDetail;
import com.example.jpa.entity.movie.Genre;
import com.example.jpa.repository.movie.MovieRepository;
import com.example.jpa.repository.movie.dto.MoviesDetailDto;
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
    private final DtoConverter dtoConvertor;

    @Cacheable(
            cacheNames = "getMovies",
            key = "'movies:genre:' + #genre + ':search:' + #search",
            cacheManager = "cacheManager"
    )
    public List<MoviesDetail> getMovies(LocalDateTime now, Boolean isNowShowing, Genre genre, String search) {
        if (!isNowShowing) now = null;
        List<MoviesDetailDto> dbResults = movieRepository.searchWithFiltering(now,genre,search);
        List<MoviesDetail> detailsList = dtoConvertor.moviesNowScreening(dbResults);

        return detailsList.stream()
                .sorted(Comparator.comparing(MoviesDetail::releaseDate).reversed())
                .toList();
    }

}
