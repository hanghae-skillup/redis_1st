package com.movie.moviedomain.movie;

import com.movie.moviedomain.movie.dto.info.MovieInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

//    private final MovieRepository movieRepository;

    public List<MovieInfo.Get> getMovies() {
        return null;
    }
}
