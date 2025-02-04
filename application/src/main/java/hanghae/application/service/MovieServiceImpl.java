package hanghae.application.service;

import hanghae.application.dto.response.MoviePlayingResponse;
import hanghae.application.port.MovieService;
import hanghae.domain.port.MovieRepository;
import hanghae.domain.port.MovieScheduleScreenDto;
import hanghae.domain.type.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    @Cacheable(
            cacheNames = "findMoviesPlaying",
            key = "(#title ?: '') + ':' + (#genre ?: '')"
    )
    public List<MoviePlayingResponse> findMoviesPlayingWithFilters(String title, Genre genre) {
        List<MovieScheduleScreenDto> movieScheduleScreenDtoList =
                movieRepository.findMoviesPlayingWithFilters(LocalDate.now(), title, genre);

        return movieScheduleScreenDtoList.stream()
                .map(MoviePlayingResponse::from)
                .toList();
    }
}
