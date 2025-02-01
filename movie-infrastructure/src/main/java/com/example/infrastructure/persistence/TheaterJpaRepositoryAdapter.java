package com.example.infrastructure.persistence;

import com.example.application.port.out.TheaterRepositoryPort;
import com.example.domain.model.entity.Theater;
import com.example.infrastructure.db.TheaterJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TheaterJpaRepositoryAdapter implements TheaterRepositoryPort {

    private final TheaterJpaRepository theaterJpaRepository;

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterJpaRepository.findById(id);
    }

}
