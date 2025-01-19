package com.movie.domain.repository;

import com.movie.domain.entity.Theater;
import java.util.List;

public interface TheaterRepository {
    Theater save(Theater theater);
    List<Theater> findAll();
}