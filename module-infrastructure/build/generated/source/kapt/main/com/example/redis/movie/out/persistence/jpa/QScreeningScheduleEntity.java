package com.example.redis.movie.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreeningScheduleEntity is a Querydsl query type for ScreeningScheduleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreeningScheduleEntity extends EntityPathBase<ScreeningScheduleEntity> {

    private static final long serialVersionUID = 535250924L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreeningScheduleEntity screeningScheduleEntity = new QScreeningScheduleEntity("screeningScheduleEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMovieTheaterEntity movieTheater;

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QScreeningScheduleEntity(String variable) {
        this(ScreeningScheduleEntity.class, forVariable(variable), INITS);
    }

    public QScreeningScheduleEntity(Path<ScreeningScheduleEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreeningScheduleEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreeningScheduleEntity(PathMetadata metadata, PathInits inits) {
        this(ScreeningScheduleEntity.class, metadata, inits);
    }

    public QScreeningScheduleEntity(Class<? extends ScreeningScheduleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movieTheater = inits.isInitialized("movieTheater") ? new QMovieTheaterEntity(forProperty("movieTheater"), inits.get("movieTheater")) : null;
    }

}

