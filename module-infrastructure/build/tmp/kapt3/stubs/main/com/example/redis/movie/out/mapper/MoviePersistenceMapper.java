package com.example.redis.movie.out.mapper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/redis/movie/out/mapper/MoviePersistenceMapper;", "", "()V", "Companion", "module-infrastructure"})
public final class MoviePersistenceMapper {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.redis.movie.out.mapper.MoviePersistenceMapper.Companion Companion = null;
    
    public MoviePersistenceMapper() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\t\u00a8\u0006\n"}, d2 = {"Lcom/example/redis/movie/out/mapper/MoviePersistenceMapper$Companion;", "", "()V", "toMovieDomain", "Lcom/example/redis/movie/Movie;", "entity", "Lcom/example/redis/movie/out/persistence/jpa/MovieEntity;", "toScreeningDomain", "Lcom/example/redis/movie/Screening;", "Lcom/example/redis/movie/out/persistence/jpa/ScreeningEntity;", "module-infrastructure"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.Movie toMovieDomain(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.out.persistence.jpa.MovieEntity entity) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.redis.movie.Screening toScreeningDomain(@org.jetbrains.annotations.NotNull()
        com.example.redis.movie.out.persistence.jpa.ScreeningEntity entity) {
            return null;
        }
    }
}