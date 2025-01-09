package com.dpflsy.movie.service;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieResponse> getNowPlayingMovies() {
        return movieRepository.findAllByOrderByReleaseDateDesc()
                .stream()
                .map(movie -> {
                    MovieResponse response = new MovieResponse();
                    response.setId(movie.getId());
                    response.setTitle(movie.getTitle());
                    response.setGenre(movie.getGenre());
                    response.setThumbnailUrl(movie.getThumbnailUrl());
                    response.setReleaseDate(movie.getReleaseDate().toString());
                    response.setRuntime(movie.getRuntime());
                    response.setRating(movie.getRating().getName());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
