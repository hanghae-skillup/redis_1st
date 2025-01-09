package com.example.hanghaecinema.service;

import com.example.hanghaecinema.controller.dto.MovieResponseDto;
import com.example.hanghaecinema.domain.Screening;
import com.example.hanghaecinema.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public List<MovieResponseDto> getNowShowingMovies() {

        List<Screening> screenings = screeningRepository.findAllScreeningByShowDateAfter(LocalDate.now());
        return screenings.stream()
                .map(MovieResponseDto::of).toList();
    }
}
