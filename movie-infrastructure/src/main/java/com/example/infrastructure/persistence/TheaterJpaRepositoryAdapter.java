package com.example.infrastructure.persistence;

import com.example.application.port.out.TheaterRepositoryPort;
import com.example.domain.model.entity.Theater;
import com.example.infrastructure.db.TheaterJpaRepository;
import java.util.Optional;
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
public class TheaterJpaRepositoryAdapter implements TheaterRepositoryPort {

    private final TheaterJpaRepository theaterJpaRepository;

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterJpaRepository.findById(id);
    }


}
