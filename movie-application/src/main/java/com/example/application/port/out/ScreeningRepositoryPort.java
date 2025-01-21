package com.example.application.port.out;

import com.example.domain.model.entity.Screening;

/**
 * Represents a port for accessing movie data from a persistence layer.
 * This interface provides an abstraction that allows the application layer
 * to interact with various repository implementations
 * (e.g., databases, external APIs).
 */
public interface ScreeningRepositoryPort {
    void save(Screening screening);
}
