package com.movie.storage.movie.repository;

import com.movie.domain.movie.MovieRepository;
import com.movie.domain.movie.dto.command.MovieCommand;
import com.movie.domain.movie.dto.info.MovieInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    public List<MovieInfo.Get> getMoviesBySearch(MovieCommand.Search search) {
        return List.of();
    }
}
