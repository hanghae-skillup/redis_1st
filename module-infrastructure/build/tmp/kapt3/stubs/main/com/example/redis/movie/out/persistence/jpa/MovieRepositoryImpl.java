package com.example.redis.movie.out.persistence.jpa;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0014\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0016J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieRepositoryImpl;", "Lcom/example/redis/movie/out/persistence/jpa/MovieRepositoryCustom;", "queryFactory", "Lcom/querydsl/jpa/impl/JPAQueryFactory;", "(Lcom/querydsl/jpa/impl/JPAQueryFactory;)V", "eqMovieGenre", "Lcom/querydsl/core/types/dsl/BooleanExpression;", "genre", "", "eqTitle", "title", "findByOrderByReleaseDateDesc", "", "Lcom/example/redis/movie/query/MovieProjection;", "module-infrastructure"})
public class MovieRepositoryImpl implements com.example.redis.movie.out.persistence.jpa.MovieRepositoryCustom {
    @org.jetbrains.annotations.NotNull()
    private final com.querydsl.jpa.impl.JPAQueryFactory queryFactory = null;
    
    public MovieRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.querydsl.jpa.impl.JPAQueryFactory queryFactory) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.redis.movie.query.MovieProjection> findByOrderByReleaseDateDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.querydsl.core.types.dsl.BooleanExpression eqTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.querydsl.core.types.dsl.BooleanExpression eqMovieGenre(@org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
}