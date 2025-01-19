package com.example.redis.movie.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieEntity is a Querydsl query type for MovieEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieEntity extends EntityPathBase<MovieEntity> {

    private static final long serialVersionUID = 1109287471L;

    public static final QMovieEntity movieEntity = new QMovieEntity("movieEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final EnumPath<com.example.redis.movie.FilmRatings> filmRatings = createEnum("filmRatings", com.example.redis.movie.FilmRatings.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MovieGenreEntity, QMovieGenreEntity> movieGenre = this.<MovieGenreEntity, QMovieGenreEntity>createList("movieGenre", MovieGenreEntity.class, QMovieGenreEntity.class, PathInits.DIRECT2);

    public final ListPath<MovieTheaterEntity, QMovieTheaterEntity> movieTheaters = this.<MovieTheaterEntity, QMovieTheaterEntity>createList("movieTheaters", MovieTheaterEntity.class, QMovieTheaterEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> releaseDate = createDateTime("releaseDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> runningTime = createNumber("runningTime", Long.class);

    public final StringPath thumbnailImagePath = createString("thumbnailImagePath");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QMovieEntity(String variable) {
        super(MovieEntity.class, forVariable(variable));
    }

    public QMovieEntity(Path<MovieEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovieEntity(PathMetadata metadata) {
        super(MovieEntity.class, metadata);
    }

}

