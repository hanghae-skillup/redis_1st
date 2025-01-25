package com.example.redis.movie.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreeningEntity is a Querydsl query type for ScreeningEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreeningEntity extends EntityPathBase<ScreeningEntity> {

    private static final long serialVersionUID = 660670581L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreeningEntity screeningEntity = new QScreeningEntity("screeningEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMovieEntity movie;

    public final ListPath<ReservationEntity, QReservationEntity> reservations = this.<ReservationEntity, QReservationEntity>createList("reservations", ReservationEntity.class, QReservationEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final com.example.redis.theater.out.persistence.jpa.QTheaterEntity theater;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QScreeningEntity(String variable) {
        this(ScreeningEntity.class, forVariable(variable), INITS);
    }

    public QScreeningEntity(Path<ScreeningEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreeningEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreeningEntity(PathMetadata metadata, PathInits inits) {
        this(ScreeningEntity.class, metadata, inits);
    }

    public QScreeningEntity(Class<? extends ScreeningEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovieEntity(forProperty("movie")) : null;
        this.theater = inits.isInitialized("theater") ? new com.example.redis.theater.out.persistence.jpa.QTheaterEntity(forProperty("theater")) : null;
    }

}

