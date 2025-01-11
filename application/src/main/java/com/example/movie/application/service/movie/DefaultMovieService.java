package com.example.movie.application.service.movie;

import com.example.movie.application.service.movie.dto.MovieResult;
import com.example.movie.domain.movie.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieResult> searchMovies() {
        return movieRepository.findAll(Sort.by(Order.desc("releaseDate"))).stream()
            .map(MovieResult::of).collect(Collectors.toList());
    }
}
