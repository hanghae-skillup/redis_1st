package com.example.app.movie.out.persistence.mapper;

import com.example.app.movie.Movie;
import com.example.app.movie.Showtime;
import com.example.app.movie.Theater;
import com.example.app.movie.out.persistence.entity.MovieJpaEntity;
import com.example.app.movie.out.persistence.entity.MovieTheaterJpaEntity;
import com.example.app.movie.out.persistence.entity.ShowtimeJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    public Movie MovieJpaEntityToMovie(MovieJpaEntity movieJpaEntity) {
        var showtimes = movieJpaEntity.getShowtimes()
                .stream()
                .map(this::ShowtimeJpaEntityToShowtime)
                .toList();

        var theaters = movieJpaEntity.getMovieTheaters()
                .stream()
                .map(this::MovieTheaterJpaEntityToTheater)
                .toList();

        return Movie.builder()
                .id(movieJpaEntity.getId())
                .title(movieJpaEntity.getTitle())
                .description(movieJpaEntity.getDescription())
                .status(movieJpaEntity.getStatus())
                .rating(movieJpaEntity.getRating())
                .genre(movieJpaEntity.getGenre())
                .thumbnail(movieJpaEntity.getThumbnail())
                .runningTime(movieJpaEntity.getRunningTime())
                .releaseDate(movieJpaEntity.getReleaseDate())
                .showtimes(showtimes)
                .theaters(theaters)
                .build();
    }

    private Showtime ShowtimeJpaEntityToShowtime(ShowtimeJpaEntity showtimeJpaEntity) {
        return new Showtime(showtimeJpaEntity.getStart(), showtimeJpaEntity.getEnd());
    }

    private Theater MovieTheaterJpaEntityToTheater(MovieTheaterJpaEntity movieTheaterJpaEntity) {
        return new Theater(movieTheaterJpaEntity.getTheater().getName());
    }
}
