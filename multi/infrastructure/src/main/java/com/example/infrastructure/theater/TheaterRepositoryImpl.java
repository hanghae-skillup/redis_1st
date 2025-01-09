package com.example.infrastructure.theater;

import com.example.domain.theater.Theater;
import com.example.domain.theater.service.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TheaterRepositoryImpl implements TheaterRepository {

    private final TheaterJpaRepository theaterJpaRepository;

    @Override
    public Theater getTheaterInfo(Long theaterId) {
        return theaterJpaRepository.findById(theaterId).orElse(null);
    }
}

