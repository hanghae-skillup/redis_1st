package org.example.port.inbound;

import org.example.domain.Screening;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningPort {
    List<Screening> findAllScreeningByShowDateAfter(LocalDate date);
}
