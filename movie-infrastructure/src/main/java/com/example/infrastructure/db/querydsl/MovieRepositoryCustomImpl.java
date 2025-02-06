package com.example.infrastructure.db.querydsl;

import com.example.application.dto.request.MovieSearchCriteria;
import com.example.domain.model.entity.Movie;
import com.example.domain.model.entity.QMovie;
import com.example.domain.model.entity.QScreening;
import com.example.domain.model.entity.QTheater;
import com.example.domain.model.valueObject.Genre;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.JPQLQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryCustomImpl extends QuerydslRepositorySupport implements MovieRepositoryCustom {

    public MovieRepositoryCustomImpl() {
        super(Movie.class); // Movie 엔티티를 Querydsl 지원 대상으로 지정
    }

    @Override
    public List<Movie> findByFilters(MovieSearchCriteria movieSearchCriteria, Sort sort) {
        QMovie movie = QMovie.movie;
        QScreening screening = QScreening.screening;
        QTheater theater = QTheater.theater;

        String title = movieSearchCriteria.title();
        Genre genre = movieSearchCriteria.genre();

        // 동적 WHERE 조건 처리
        BooleanBuilder builder = new BooleanBuilder();
        if (title != null && !title.isEmpty()) {
            builder.and(movie.title.contains(title));
        }
        if (genre != null) {
            builder.and(movie.genre.eq(genre));
        }

        // Querydsl 쿼리 생성 (엔티티 자체 조회)
        JPQLQuery<Movie> query = from(movie)
                .leftJoin(movie.screenings, screening).fetchJoin()
                .leftJoin(screening.theater, theater).fetchJoin()
                .where(builder);

        // 동적 정렬 적용
        applySorting(query, sort, movie);

        return query.fetch();
    }

    /**
     * QueryDSL 동적 정렬 적용 메서드:
     * 주어진 정렬 조건인 `Sort`를 기반으 쿼리에 정렬을 적용한다.
     * 여러 개의 정렬이 있을 경우 순서를 유지한다.
     * `ALLOWED_SORT_FIELDS`에 명시된 필드만 정렬 조건으로 허용한다.
     */

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("releaseDate", "runtimeMinutes"); // 정렬 가능한 필드를 명시

    private void applySorting(JPQLQuery<Movie> query, Sort sort, QMovie movie) {
        if (sort.isUnsorted()) {
            query.orderBy(movie.releaseDate.asc()); // 기본 정렬 유지
            return;
        }

        PathBuilder<Movie> entityPath = new PathBuilder<>(Movie.class, "movie");
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>(); // 여러 정렬 조건을 순서를 유지해 저장할 리스트 생성

        for (Sort.Order order : sort) {
            String property = order.getProperty();

            if (!ALLOWED_SORT_FIELDS.contains(property)) {
                continue; // 허용되지 않은 정렬 필드는 무시
            }

            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            SimplePath<Comparable> path = Expressions.path(Comparable.class, entityPath, property); // 정렬할 필드 반환
            orderSpecifiers.add(new OrderSpecifier<>(direction, path)); // 정렬 조건을 리스트에 추가
        }
        query.orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]));

    }

}
