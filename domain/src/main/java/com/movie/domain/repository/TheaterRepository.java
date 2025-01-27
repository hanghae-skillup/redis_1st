package com.movie.domain.repository;

import com.movie.domain.entity.Theater;
import java.util.List;
import java.util.Optional;

public interface TheaterRepository {
    Theater save(Theater theater);
    List<Theater> findAll();
    Optional<Theater> findById(Long id);
    Optional<String> findNameById(Long id);
}