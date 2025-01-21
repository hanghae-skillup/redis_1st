package com.movie.storage.movie.repository;

import com.movie.domain.movie.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TheaterRepositoryImpl implements TheaterRepository {

    private final TheaterJpaRepository theaterJpaRepository;

}
