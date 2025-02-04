package hanghae.domain.port;

import hanghae.domain.type.Genre;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository {
    List<MovieScheduleScreenDto> findMoviesPlayingWithFilters(
            LocalDate now,
            String title,
            Genre genre
    );
}
