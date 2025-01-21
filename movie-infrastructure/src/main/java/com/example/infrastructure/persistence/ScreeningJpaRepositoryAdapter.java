package com.example.infrastructure.persistence;

import com.example.application.port.out.ScreeningRepositoryPort;
import com.example.domain.model.entity.Screening;
import com.example.infrastructure.db.ScreeningJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * This adapter serves as a bridge between the application layer
 * and the database layer, allowing the application to access movie
 * data through the defined MovieRepositoryPort interface while
 * encapsulating the JPA-specific implementation details.
 */

@RequiredArgsConstructor
@Repository
public class ScreeningJpaRepositoryAdapter implements ScreeningRepositoryPort {

    private final ScreeningJpaRepository screeningJpaRepository;

    @Override
    public void save(Screening screening) {
        screeningJpaRepository.save(screening);
    }

}
