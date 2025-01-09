package com.example.domain.theater.service;

import com.example.domain.theater.Theater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterServiceRepository;

    public Theater getTheaterInfo(Long theaterId) {
        return theaterServiceRepository.getTheaterInfo(theaterId);
    }
}
