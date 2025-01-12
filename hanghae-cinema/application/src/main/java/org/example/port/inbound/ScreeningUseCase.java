package org.example.port.inbound;


import org.example.port.inbound.dto.MovieResponseDto;

import java.util.List;

public interface ScreeningUseCase {
    List<MovieResponseDto> getNowShowingMovies();
}
