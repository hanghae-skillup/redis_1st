package com.example.infrastructure.db.querydsl;

import com.example.application.dto.request.MovieSearchCriteria;
import com.example.domain.model.entity.Movie;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface MovieRepositoryCustom {
    List<Movie> findByFilters(MovieSearchCriteria movieSearchCriteria, Sort sort);
}
