package hanghae.infrastructure.adapter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hanghae.domain.entity.QMovie;
import hanghae.domain.entity.QSchedule;
import hanghae.domain.entity.QScreen;
import hanghae.domain.entity.QScreenSchedule;
import hanghae.domain.port.MovieRepository;
import hanghae.domain.port.MovieScheduleScreenDto;
import hanghae.domain.type.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaMovieRepositoryAdapter implements MovieRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MovieScheduleScreenDto> findMoviesPlayingWithFilters(
            LocalDate now,
            String title,
            Genre genre
    ) {

        QMovie movie = QMovie.movie;
        QSchedule schedule = QSchedule.schedule;
        QScreen screen = QScreen.screen;
        QScreenSchedule screenSchedule = QScreenSchedule.screenSchedule;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(movie.releaseDate.loe(now))
                .and(movie.deletedAt.isNull());

        if (title != null && !title.trim().isEmpty()) {
            builder.and(movie.title.contains(title));
        }

        if (genre != null) {
            builder.and(movie.genre.eq(genre));
        }

        return queryFactory
                .select(
                        Projections.constructor(
                                MovieScheduleScreenDto.class,
                                movie.movieId,
                                movie.title,
                                movie.ageRating,
                                movie.releaseDate,
                                movie.thumbnailUrl,
                                movie.runningTime,
                                movie.genre,
                                schedule.scheduleId,
                                schedule.startDateTime,
                                schedule.endDateTime,
                                screen.screenId,
                                screen.screenName
                        )
                )
                .from(movie)
                .leftJoin(movie.schedules, schedule)
                .leftJoin(schedule.screenSchedules, screenSchedule)
                .leftJoin(screenSchedule.screen, screen)
                .where(builder)
                .orderBy(
                        movie.releaseDate.desc(),
                        schedule.startDateTime.asc(),
                        screen.screenName.asc()
                )
                .fetch();
    }
}
