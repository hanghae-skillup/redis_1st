package com.example.redis.theater.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeatEntity is a Querydsl query type for SeatEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeatEntity extends EntityPathBase<SeatEntity> {

    private static final long serialVersionUID = 1850847691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeatEntity seatEntity = new QSeatEntity("seatEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    public final StringPath col = createString("col");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath row = createString("row");

    public final QTheaterEntity theater;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QSeatEntity(String variable) {
        this(SeatEntity.class, forVariable(variable), INITS);
    }

    public QSeatEntity(Path<SeatEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSeatEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSeatEntity(PathMetadata metadata, PathInits inits) {
        this(SeatEntity.class, metadata, inits);
    }

    public QSeatEntity(Class<? extends SeatEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.theater = inits.isInitialized("theater") ? new QTheaterEntity(forProperty("theater")) : null;
    }

}

