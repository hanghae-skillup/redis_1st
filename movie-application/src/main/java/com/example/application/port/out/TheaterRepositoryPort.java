package com.example.application.port.out;

import com.example.domain.model.entity.Theater;
import java.util.Optional;

public interface TheaterRepositoryPort {
    Optional<Theater> findById(Long id);
}
