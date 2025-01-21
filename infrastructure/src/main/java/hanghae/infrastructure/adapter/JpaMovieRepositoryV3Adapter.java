package hanghae.infrastructure.adapter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanghae.domain.domain.Movie;
import hanghae.domain.port.MovieRepositoryV3;
import hanghae.domain.types.movie.Genre;
import hanghae.infrastructure.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMovieRepositoryV3Adapter implements MovieRepositoryV3 {

    private final JPAQueryFactory queryFactory;

    @Override
    // TODO 아직 캐시 설정 마무리하지 않음
    @Cacheable(
            cacheNames = "findMoviesPlaying",
            key = "#now.toLocalDate() + '::' + (#title ?: '') + '::' + (#genre ?: '')"
    )
    public List<Movie> findMoviesPlayingWithFilters(
            LocalDateTime now,
            String title,
            String genre
    ) {

        QMovieEntity movie = QMovieEntity.movieEntity;
        QShowtimeEntity showtime = QShowtimeEntity.showtimeEntity;
        QTheaterShowtimeEntity ts = QTheaterShowtimeEntity.theaterShowtimeEntity;
        QTheaterEntity theater = QTheaterEntity.theaterEntity;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(movie.releaseDate.loe(now.toLocalDate()))
                .and(movie.deletedAt.isNull().or(movie.deletedAt.gt(now)));

        if (title != null && !title.trim().isEmpty()) {
            builder.and(movie.title.containsIgnoreCase(title));
        }

        if (genre != null && !genre.trim().isEmpty()) {
            try {
                Genre toGenreEnum = Genre.valueOf(genre.toUpperCase());
                builder.and(movie.genre.eq(toGenreEnum));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid Genre: " + genre);
            }
        }

        List<MovieEntity> movies = queryFactory
                .selectDistinct(movie)
                .from(movie)
                .leftJoin(movie.showtime, showtime).fetchJoin()
                .leftJoin(showtime.theaterShowtime, ts).fetchJoin()
                .leftJoin(ts.theater, theater).fetchJoin()
                .where(builder)
                .orderBy(movie.releaseDate.desc())
                .fetch();

        return movies.stream()
                .map(MovieEntity::toMovieDomain)
                .toList();
    }

    @CacheEvict(cacheNames = "findMoviesPlaying", allEntries = true)
    public void clearCache() {}
}
