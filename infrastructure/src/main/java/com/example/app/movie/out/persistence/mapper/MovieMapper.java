package com.example.app.movie.out.persistence.mapper;

import com.example.app.movie.domain.Movie;
import com.example.app.movie.domain.Showtime;
import com.example.app.movie.domain.Theater;
import com.example.app.movie.out.persistence.entity.MovieEntity;
import com.example.app.movie.out.persistence.entity.MovieTheaterEntity;
import com.example.app.movie.out.persistence.entity.ShowtimeEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public Movie movieEntityToMovie(MovieEntity movieEntity) {
        var showtimes = movieEntity.getShowtimes()
                .stream()
                .map(this::showtimeJpaEntityToShowtime)
                .toList();

        var theaters = movieEntity.getMovieTheaters()
                .stream()
                .map(this::movieTheaterJpaEntityToTheater)
                .toList();

        return Movie.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .description(movieEntity.getDescription())
                .status(movieEntity.getStatus())
                .rating(movieEntity.getRating())
                .genre(movieEntity.getGenre())
                .thumbnail(movieEntity.getThumbnail())
                .runningTime(movieEntity.getRunningTime())
                .releaseDate(movieEntity.getReleaseDate())
                .showtimes(showtimes)
                .theaters(theaters)
                .build();
    }

    private Showtime showtimeJpaEntityToShowtime(ShowtimeEntity showtimeEntity) {
        return new Showtime(showtimeEntity.getStart(), showtimeEntity.getEnd());
    }

    private Theater movieTheaterJpaEntityToTheater(MovieTheaterEntity movieTheaterJpaEntity) {
        return new Theater(movieTheaterJpaEntity.getTheater().getName());
    }
}
