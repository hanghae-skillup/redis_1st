package hanghae.application.service;

import hanghae.application.dto.MovieResponse;
import hanghae.application.port.MovieService;
import hanghae.common.exception.NoContentsException;
import hanghae.domain.domain.Movie;
import hanghae.domain.domain.Showtime;
import hanghae.domain.domain.Theater;
import hanghae.domain.port.MovieRepository;
import hanghae.domain.port.ShowtimeRepository;
import hanghae.domain.port.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;
    private final TheaterRepository theaterRepository;

    @Override
    public List<MovieResponse> findMoviesPlaying() {
        List<Movie> movieList = movieRepository.findMoviesPlaying(LocalDateTime.now())
                .orElseThrow(() -> new NoContentsException("movie"));

        return movieList.stream()
                .map(this::saveShowtimeInMoviePlaying)
                .map(this::saveTheaterInMoviePlaying)
                .map(MovieResponse::from)
                .toList();
    }

    private Movie saveShowtimeInMoviePlaying(Movie movie) {
        List<Showtime> showtimeList = findShowtimeOfMoviePlaying(movie);
        return movie.setShowtime(showtimeList);
    }

    private List<Showtime> findShowtimeOfMoviePlaying(Movie movie) {
        return showtimeRepository.findShowtimeByMovie(movie);
    }

    private Movie saveTheaterInMoviePlaying(Movie movie) {
        List<Theater> theaterList = findTheaterOfMoviePlaying(movie);
        return movie.setTheater(theaterList);
    }

    private List<Theater> findTheaterOfMoviePlaying(Movie movie) {
        return theaterRepository.findTheaterByMovie(movie)
                .orElseThrow(() -> new NoContentsException("theater"));
    }
}
