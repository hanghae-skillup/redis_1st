package com.example.infrastructure.db.querydsl;

import com.example.domain.model.entity.Movie;
import com.example.domain.model.valueObject.Genre;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface MovieRepositoryCustom {
    List<Movie> findByFilters(String title, Genre genre, Sort sort);
}
