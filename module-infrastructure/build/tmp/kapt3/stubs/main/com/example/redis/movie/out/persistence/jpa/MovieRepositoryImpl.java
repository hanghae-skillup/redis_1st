package com.example.redis.movie.out.persistence.jpa;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieRepositoryImpl;", "Lcom/example/redis/movie/out/persistence/jpa/MovieRepositoryCustom;", "queryFactory", "Lcom/querydsl/jpa/impl/JPAQueryFactory;", "(Lcom/querydsl/jpa/impl/JPAQueryFactory;)V", "eqMovieGenre", "Lcom/querydsl/core/types/dsl/BooleanExpression;", "movieIds", "", "", "findByOrderByReleaseDateDesc", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "title", "", "genre", "likeTitle", "module-infrastructure"})
public class MovieRepositoryImpl implements com.example.redis.movie.out.persistence.jpa.MovieRepositoryCustom {
    @org.jetbrains.annotations.NotNull()
    private final com.querydsl.jpa.impl.JPAQueryFactory queryFactory = null;
    
    public MovieRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.querydsl.jpa.impl.JPAQueryFactory queryFactory) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.redis.movie.out.persistence.jpa.MovieEntity> findByOrderByReleaseDateDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String genre) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.querydsl.core.types.dsl.BooleanExpression likeTitle(@org.jetbrains.annotations.Nullable()
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.querydsl.core.types.dsl.BooleanExpression eqMovieGenre(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> movieIds) {
        return null;
    }
}