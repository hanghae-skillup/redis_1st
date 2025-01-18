package com.example.redis.movie.out.persistence.jpa;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/example/redis/movie/out/persistence/jpa/MovieRepositoryCustom;", "", "findByOrderByReleaseDateDesc", "", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "title", "", "genre", "module-infrastructure"})
public abstract interface MovieRepositoryCustom {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.redis.movie.out.persistence.jpa.MovieEntity> findByOrderByReleaseDateDesc(@org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.String genre);
}