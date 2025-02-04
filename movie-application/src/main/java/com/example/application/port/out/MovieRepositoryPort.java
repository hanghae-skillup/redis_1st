package com.example.application.port.out;

import com.example.application.dto.request.MovieSearchCriteria;
import com.example.domain.model.entity.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

public interface MovieRepositoryPort {
    List<Movie> findBy(MovieSearchCriteria movieSearchCriteria, Sort sort);
    void save(Movie movie);
    Optional<Movie> findById(Long id);
}
