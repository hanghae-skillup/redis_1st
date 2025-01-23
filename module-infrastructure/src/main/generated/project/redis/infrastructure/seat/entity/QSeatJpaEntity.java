package project.redis.infrastructure.seat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeatJpaEntity is a Querydsl query type for SeatJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeatJpaEntity extends EntityPathBase<SeatJpaEntity> {

    private static final long serialVersionUID = -344098188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSeatJpaEntity seatJpaEntity = new QSeatJpaEntity("seatJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    public final project.redis.infrastructure.cinema.entity.QCinemaJpaEntity cinema;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath seatNumber = createString("seatNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QSeatJpaEntity(String variable) {
        this(SeatJpaEntity.class, forVariable(variable), INITS);
    }

    public QSeatJpaEntity(Path<? extends SeatJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSeatJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSeatJpaEntity(PathMetadata metadata, PathInits inits) {
        this(SeatJpaEntity.class, metadata, inits);
    }

    public QSeatJpaEntity(Class<? extends SeatJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinema = inits.isInitialized("cinema") ? new project.redis.infrastructure.cinema.entity.QCinemaJpaEntity(forProperty("cinema")) : null;
    }

}

