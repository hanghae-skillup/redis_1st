package com.example.infrastructure.screening;

import com.example.domain.movies.entity.QMovie;
import com.example.domain.movies.entity.enums.Genre;
import com.example.domain.screening.entity.QScreening;
import com.example.domain.screening.entity.QScreeningResponseDTO;
import com.example.domain.screening.entity.Screening;
import com.example.domain.screening.entity.ScreeningResponseDTO;
import com.example.domain.screening.service.ScreeningRepository;
import com.example.domain.theater.entity.QTheater;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
@Primary
public class ScreeningQueryDslRepositoryImpl implements ScreeningRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Screening getScreeningInfo(Long screeningId) {
        return null;
    }

    @Override
    public List<ScreeningResponseDTO> getAllScreeningInfo(String movieName, Genre genre) {
        QScreening screening = QScreening.screening;
        QMovie movie = QMovie.movie;
        QTheater theater = QTheater.theater;

        BooleanBuilder builder = new BooleanBuilder();
        if(movieName!=null){
            builder.and(movie.title.eq(movieName));
        }
        if(genre!=null){
            builder.and(movie.genre.eq(genre));
        }
        return jpaQueryFactory
                .select(new QScreeningResponseDTO(
                        movie.title,
                        movie.title,
                        movie.rating.stringValue(),
                        movie.releaseDate,
                        movie.thumbnailUrl,
                        movie.runningTime,
                        movie.genre.stringValue(),
                        theater.theaterName,
                        screening.startTime,
                        screening.endTime,
                        screening.screeningDate
                ))
                .from(screening)
                .join(screening.movie, movie)
                .join(screening.theater, theater)
                .where(builder)
                .orderBy(movie.releaseDate.asc(),
                        screening.screeningDate.asc(),
                        screening.startTime.asc())
                .fetch();
    }



    @Override
    public void saveScreening(Screening screening) {

    }
}
