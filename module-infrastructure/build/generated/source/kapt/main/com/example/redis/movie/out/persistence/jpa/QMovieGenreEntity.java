package com.example.redis.movie.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieGenreEntity is a Querydsl query type for MovieGenreEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieGenreEntity extends EntityPathBase<MovieGenreEntity> {

    private static final long serialVersionUID = 1079114874L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieGenreEntity movieGenreEntity = new QMovieGenreEntity("movieGenreEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMovieEntity movie;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QMovieGenreEntity(String variable) {
        this(MovieGenreEntity.class, forVariable(variable), INITS);
    }

    public QMovieGenreEntity(Path<MovieGenreEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieGenreEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieGenreEntity(PathMetadata metadata, PathInits inits) {
        this(MovieGenreEntity.class, metadata, inits);
    }

    public QMovieGenreEntity(Class<? extends MovieGenreEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovieEntity(forProperty("movie")) : null;
    }

}

