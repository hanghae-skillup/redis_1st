package project.redis.presentation.movie.mapper;

import project.redis.domain.movie.Movie;
import project.redis.presentation.movie.dto.response.MovieResponse;

public class MovieApiMapper {

    public static MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .rating(movie.getRating().toString())
                .releaseDate(movie.getReleaseDate())
                .runningTimeMin(movie.getRunningMinTime())
                .genreName(movie.getGenre().getGenreName())
                .build();
    }
}
