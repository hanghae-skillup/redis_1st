package com.example.application.port.out;

import com.example.domain.model.entity.Screening;
import java.util.List;
import java.util.Optional;

public interface ScreeningRepositoryPort {
    void save(Screening screening);
    Optional<Screening> findById(Long id);
    List<Screening> findAll();
}
