package com.example.infrastructure.screening;

import com.example.domain.screening.Screening;
import com.example.domain.screening.service.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ScreeningRepositoryImpl implements ScreeningRepository {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public Screening getScreeningInfo(Long screeningId) {
        return screeningJpaRepository.findById(screeningId).orElse(null);
    }

    @Override
    public List<Screening> getAllScreeningInfo() {
        return screeningJpaRepository.findAll();
    }

    @Override
    public void saveScreening(Screening screening) {
        screeningJpaRepository.save(screening);
    }
}
