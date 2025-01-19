package com.example.repository.movie;

import com.example.entity.Genre;
import com.example.repository.movie.dto.MovieDto;
import com.example.repository.movie.dto.QMovieDto;
import com.example.repository.movie.dto.QScreeningInfoDto;
import com.example.repository.movie.dto.ScreeningInfoDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.entity.QMovie.movie;
import static com.example.entity.QScreening.screening;
import static com.example.entity.QTheater.theater;

@Slf4j
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MovieRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<MovieDto> findAllByTitleAndGenre(String title, String genre) {
        List<MovieDto> allMovie = findAllMovie(title, genre);

        Map<Long, List<ScreeningInfoDto>> screeningInfoMap = findScreeningInfoMap(toMovieIds(allMovie));

        allMovie.forEach(movieDto -> movieDto.addScreeningInfo(screeningInfoMap.get(movieDto.getId())));

        return allMovie;
    }

    private List<MovieDto> findAllMovie(String title, String genre) {
        return queryFactory.select(new QMovieDto(movie.id, movie.title, movie.thumbnailUrl, movie.genre, movie.rating, movie.releaseDate))
                .from(movie)
                .where(titleLike(title),genreEq(genre))
                .orderBy(movie.releaseDate.desc())
                .fetch();
    }

    private Map<Long, List<ScreeningInfoDto>> findScreeningInfoMap(List<Long> movieIds) {
        return queryFactory.select(new QScreeningInfoDto(screening.movie.id, theater.name, screening.startedAt, screening.endedAt))
                .from(screening)
                .join(screening.theater, theater)
                .where(screening.movie.id.in(movieIds))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(ScreeningInfoDto::getMovieId));
    }

    private List<Long> toMovieIds(List<MovieDto> movieDtos) {
        return movieDtos.stream()
                .map(MovieDto::getId).toList();
    }

    private Predicate titleLike(String title) {
        if (title == null) {
            return null;
        }
        return movie.title.contains(title);
    }

    private Predicate genreEq(String genre) {
        if (genre == null) {
            return null;
        }
        return movie.genre.eq(Genre.valueOf(genre.toUpperCase()));
    }
}
