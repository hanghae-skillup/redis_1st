package com.example.infrastructure.persistence;

import com.example.application.port.out.ScreeningRepositoryPort;
import com.example.domain.model.entity.Screening;
import com.example.infrastructure.db.ScreeningJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScreeningJpaRepositoryAdapter implements ScreeningRepositoryPort {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public void save(Screening screening) {
        screeningJpaRepository.save(screening);
    }

    @Override
    public Optional<Screening> findById(Long id) {
        return screeningJpaRepository.findById(id);
    }

    @Override
    public List<Screening> findAll() {
        return screeningJpaRepository.findAll();
    }

}
