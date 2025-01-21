package com.example.application.port.out;

import com.example.domain.model.entity.Theater;
import java.util.Optional;

/**
 * Represents a port for accessing movie data from a persistence layer.
 * This interface provides an abstraction that allows the application layer
 * to interact with various repository implementations
 * (e.g., databases, external APIs).
 */
public interface TheaterRepositoryPort {
    Optional<Theater> findById(Long id);
}
