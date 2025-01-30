package com.example.redis.movie.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieTheaterEntity is a Querydsl query type for MovieTheaterEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieTheaterEntity extends EntityPathBase<MovieTheaterEntity> {

    private static final long serialVersionUID = 1651100552L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieTheaterEntity movieTheaterEntity = new QMovieTheaterEntity("movieTheaterEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMovieEntity movie;

    public final ListPath<ScreeningScheduleEntity, QScreeningScheduleEntity> screeningSchedules = this.<ScreeningScheduleEntity, QScreeningScheduleEntity>createList("screeningSchedules", ScreeningScheduleEntity.class, QScreeningScheduleEntity.class, PathInits.DIRECT2);

    public final com.example.redis.theater.out.persistence.jpa.QTheaterEntity theater;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QMovieTheaterEntity(String variable) {
        this(MovieTheaterEntity.class, forVariable(variable), INITS);
    }

    public QMovieTheaterEntity(Path<MovieTheaterEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieTheaterEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieTheaterEntity(PathMetadata metadata, PathInits inits) {
        this(MovieTheaterEntity.class, metadata, inits);
    }

    public QMovieTheaterEntity(Class<? extends MovieTheaterEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovieEntity(forProperty("movie")) : null;
        this.theater = inits.isInitialized("theater") ? new com.example.redis.theater.out.persistence.jpa.QTheaterEntity(forProperty("theater")) : null;
    }

}

