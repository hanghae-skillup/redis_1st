package org.example.port.inbound;

import lombok.RequiredArgsConstructor;
import org.example.domain.Screening;
import org.example.port.inbound.dto.MovieResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService implements ScreeningUseCase {

    private final ScreeningPort screeningPort;

    @Override
    public List<MovieResponseDto> getNowShowingMovies() {
        List<Screening> screenings = screeningPort.findAllScreeningByShowDateAfter(LocalDate.now());
        return screenings.stream()
                .map(MovieResponseDto::of).toList();
    }
}
