package project.redis.infrastructure.screening.repository;

import static org.springframework.util.StringUtils.hasText;
import static project.redis.infrastructure.cinema.entity.QCinemaJpaEntity.cinemaJpaEntity;
import static project.redis.infrastructure.genre.entity.QGenreJpaEntity.genreJpaEntity;
import static project.redis.infrastructure.movie.entity.QMovieJpaEntity.movieJpaEntity;
import static project.redis.infrastructure.screening.entity.QScreeningJpaEntity.screeningJpaEntity;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;

@Component
@RequiredArgsConstructor
public class ScreeningJpaRepositoryCustomImpl implements ScreeningJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScreeningJpaEntity> findScreeningsByFilter(LocalDate maxScreeningDate, String genreName,
                                                           String movieName) {

        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, screeningJpaEntity.screeningStartTime));
        orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, screeningJpaEntity.movie.releaseDate));

        return queryFactory.selectFrom(screeningJpaEntity)
                .leftJoin(screeningJpaEntity.movie, movieJpaEntity).fetchJoin()
                .leftJoin(screeningJpaEntity.movie.genre, genreJpaEntity).fetchJoin()
                .leftJoin(screeningJpaEntity.cinema, cinemaJpaEntity).fetchJoin()
                .where(
                        genreNameEq(genreName),
                        movieNameEq(movieName),
                        withInScreeningDay(maxScreeningDate)
                )
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
                .fetch();
    }

    private BooleanExpression withInScreeningDay(LocalDate maxScreeningDate) {
        LocalDate now = LocalDate.now();
        return screeningJpaEntity.screeningStartTime.between(now.atStartOfDay(), maxScreeningDate.atStartOfDay());
    }

    private BooleanExpression movieNameEq(String movieName) {
        return hasText(movieName)
                ? movieJpaEntity.title.eq(movieName)
                : null;
    }

    private BooleanExpression genreNameEq(String genreName) {
        return hasText(genreName)
                ? genreJpaEntity.genreName.eq(genreName)
                : null;
    }
}
