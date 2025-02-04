package project.redis.infrastructure.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationSeatJpaEntity is a Querydsl query type for ReservationSeatJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationSeatJpaEntity extends EntityPathBase<ReservationSeatJpaEntity> {

    private static final long serialVersionUID = -435679981L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationSeatJpaEntity reservationSeatJpaEntity = new QReservationSeatJpaEntity("reservationSeatJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QReservationJpaEntity reservation;

    public final project.redis.infrastructure.screening.entity.QScreeningJpaEntity screening;

    public final project.redis.infrastructure.seat.entity.QSeatJpaEntity seat;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QReservationSeatJpaEntity(String variable) {
        this(ReservationSeatJpaEntity.class, forVariable(variable), INITS);
    }

    public QReservationSeatJpaEntity(Path<? extends ReservationSeatJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationSeatJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationSeatJpaEntity(PathMetadata metadata, PathInits inits) {
        this(ReservationSeatJpaEntity.class, metadata, inits);
    }

    public QReservationSeatJpaEntity(Class<? extends ReservationSeatJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservationJpaEntity(forProperty("reservation"), inits.get("reservation")) : null;
        this.screening = inits.isInitialized("screening") ? new project.redis.infrastructure.screening.entity.QScreeningJpaEntity(forProperty("screening"), inits.get("screening")) : null;
        this.seat = inits.isInitialized("seat") ? new project.redis.infrastructure.seat.entity.QSeatJpaEntity(forProperty("seat"), inits.get("seat")) : null;
    }

}

