package com.hanghae.cinema.adapter.persistence.repository;

import com.hanghae.cinema.application.dto.MovieResponseDTO;
import com.hanghae.cinema.application.dto.ShowTimeResponseDTO;
import com.hanghae.cinema.application.dto.TheaterResponseDTO;
import com.hanghae.cinema.domain.type.MovieGenre;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hanghae.cinema.adapter.persistence.entity.QMovieEntity.movieEntity;
import static com.hanghae.cinema.adapter.persistence.entity.QShowTimeEntity.showTimeEntity;
import static com.hanghae.cinema.adapter.persistence.entity.QTheaterEntity.theaterEntity;

@RequiredArgsConstructor
@Repository
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<MovieResponseDTO> findMovie(LocalDate date, LocalDateTime dateTime, String keyword, MovieGenre movieGenre) {
        BooleanBuilder builder = new BooleanBuilder();

        if (keyword != null && !keyword.isEmpty()) {
            builder.and(movieEntity.title.eq(keyword));
        }

        if (movieGenre != null) {
            builder.and(movieEntity.genre.eq(movieGenre));
        }

        builder.and(movieEntity.releaseAt.before(date));
        builder.and(showTimeEntity.startedAt.after(dateTime));

        // 영화와 상영 시간 데이터를 함께 조회
        List<Tuple> results = jpaQueryFactory
                .select(
                        movieEntity.id,
                        movieEntity.title,
                        movieEntity.thumbNail,
                        movieEntity.rating,
                        movieEntity.releaseAt,
                        movieEntity.runningTimeMinute,
                        movieEntity.genre,
                        showTimeEntity.id,
                        showTimeEntity.startedAt,
                        showTimeEntity.endedAt,
                        theaterEntity.id,
                        theaterEntity.name
                )
                .from(movieEntity)
                .leftJoin(showTimeEntity).on(movieEntity.id.eq(showTimeEntity.movie.id))
                .leftJoin(theaterEntity).on(theaterEntity.id.eq(showTimeEntity.theater.id))
                .where(builder)
                .orderBy(movieEntity.releaseAt.desc(), showTimeEntity.startedAt.asc())
                .fetch();

        Map<Long, MovieResponseDTO> movieMap = new LinkedHashMap<>();
        results.forEach(tuple -> {
            Long movieId = tuple.get(movieEntity.id);

            movieMap.putIfAbsent(movieId, new MovieResponseDTO(
                    movieId,
                    tuple.get(movieEntity.title),
                    tuple.get(movieEntity.thumbNail),
                    tuple.get(movieEntity.rating),
                    tuple.get(movieEntity.releaseAt),
                    tuple.get(movieEntity.runningTimeMinute),
                    tuple.get(movieEntity.genre),
                    new ArrayList<>()
            ));

            MovieResponseDTO movie = movieMap.get(movieId);
            movie.getShowTime().add(new ShowTimeResponseDTO(
                    tuple.get(showTimeEntity.id),
                    tuple.get(showTimeEntity.startedAt),
                    tuple.get(showTimeEntity.endedAt),
                    new TheaterResponseDTO(
                            tuple.get(theaterEntity.id),
                            tuple.get(theaterEntity.name)
                    )
            ));
        });

        // 영화 리스트 반환
        return new ArrayList<>(movieMap.values());
    }
}
