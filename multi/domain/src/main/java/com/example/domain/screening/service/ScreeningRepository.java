package com.example.domain.screening.service;

import com.example.domain.screening.Screening;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningRepository {
    public Screening getScreeningInfo(Long screeningId);

    public List<Screening> getAllScreeningInfo();

    void saveScreening(Screening screening);
}
