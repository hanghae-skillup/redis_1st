package com.example.movie.application.service;

import com.example.movie.application.convertor.DtoConvertor;
import com.example.movie.application.dto.MoviesNowShowingDetail;
import com.example.movie.entity.movie.Genre;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.dto.MoviesNowShowingDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final DtoConvertor dtoConvertor;

    public List<MoviesNowShowingDetail> getMoviesNowShowing(LocalDateTime now, Genre genre, String search) {
        List<MoviesNowShowingDetailDto> dbResults = movieRepository.findNowShowing(now,genre,search);
        List<MoviesNowShowingDetail> detailsList = dtoConvertor.moviesNowScreening(dbResults);

        return detailsList.stream()
                .sorted(Comparator.comparing(MoviesNowShowingDetail::releaseDate).reversed())
                .toList();
    }

}
