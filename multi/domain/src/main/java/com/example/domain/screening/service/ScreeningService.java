package com.example.domain.screening.service;

import com.example.domain.movies.entity.enums.Genre;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.entity.ScreeningResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScreeningService {
    private  final ScreeningRepository screeningRepository;

    public Screening getScreeningInfo(Long screeningId) {
        return screeningRepository.getScreeningInfo(screeningId);
    }

    @Cacheable(value = "screeningCache", key = "#movieName + '_' + #genre")
    public List<ScreeningResponseDTO> getAllScreeningInfo(String movieName, Genre genre){
        return screeningRepository.getAllScreeningInfo(movieName,genre);
    }

   public void enrollScreening(Screening screening){
        screeningRepository.saveScreening(screening);
   }
}
