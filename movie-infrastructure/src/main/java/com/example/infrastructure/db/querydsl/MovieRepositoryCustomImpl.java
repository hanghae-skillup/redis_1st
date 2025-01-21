package com.example.infrastructure.db.querydsl;

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
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryCustomImpl extends QuerydslRepositorySupport implements MovieRepositoryCustom {

    public MovieRepositoryCustomImpl() {
        super(Movie.class); // Movie 엔티티를 Querydsl 지원 대상으로 지정
    }

    @Override
    public List<Movie> findByFilters(String title, Genre genre, Sort sort) {
        QMovie movie = QMovie.movie;
        QScreening screening = QScreening.screening;
        QTheater theater = QTheater.theater;

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
     * QueryDSL 동적 정렬 적용 메서드
     */
    private void applySorting(JPQLQuery<Movie> query, Sort sort, QMovie movie) {
        if (sort.isUnsorted()) {
            query.orderBy(movie.releaseDate.asc()); // 기본 정렬 유지
            return;
        }

        PathBuilder<Movie> entityPath = new PathBuilder<>(Movie.class, "movie");
        for (Sort.Order order : sort) {
            String property = order.getProperty();
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;

            // 정렬 필드를 ComparableExpressionBase로 변환
            SimplePath<Comparable> path = Expressions.path(Comparable.class, entityPath, property);

            OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(direction, path);
            query.orderBy(orderSpecifier);
        }
    }

}
