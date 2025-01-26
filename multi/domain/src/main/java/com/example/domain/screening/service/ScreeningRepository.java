package com.example.domain.screening.service;

import com.example.domain.movies.entity.enums.Genre;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.entity.ScreeningResponseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningRepository {
    public Screening getScreeningInfo(Long screeningId);

    public List<ScreeningResponseDTO> getAllScreeningInfo(String movieName, Genre genre);

    void saveScreening(Screening screening);
}
