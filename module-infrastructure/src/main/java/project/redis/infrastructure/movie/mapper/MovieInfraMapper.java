package project.redis.infrastructure.movie.mapper;


import project.redis.domain.movie.Movie;
import project.redis.infrastructure.genre.mapper.GenreInfraMapper;
import project.redis.infrastructure.movie.entity.MovieJpaEntity;

public class MovieInfraMapper {

    public static Movie toMovie(MovieJpaEntity movie) {
        return Movie.generateMovie(
                movie.getId(),
                movie.getTitle(),
                movie.getRating(),
                movie.getReleaseDate(),
                movie.getThumbnailUrl(),
                movie.getRunningMinTime(),
                GenreInfraMapper.toGenre(movie.getGenre())
        );
    }

}
