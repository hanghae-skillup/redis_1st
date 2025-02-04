package project.redis.infrastructure.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationJpaEntity is a Querydsl query type for ReservationJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationJpaEntity extends EntityPathBase<ReservationJpaEntity> {

    private static final long serialVersionUID = 635228824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationJpaEntity reservationJpaEntity = new QReservationJpaEntity("reservationJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final DateTimePath<java.time.LocalDateTime> reservationTime = createDateTime("reservationTime", java.time.LocalDateTime.class);

    public final project.redis.infrastructure.screening.entity.QScreeningJpaEntity screening;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public final StringPath username = createString("username");

    public QReservationJpaEntity(String variable) {
        this(ReservationJpaEntity.class, forVariable(variable), INITS);
    }

    public QReservationJpaEntity(Path<? extends ReservationJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationJpaEntity(PathMetadata metadata, PathInits inits) {
        this(ReservationJpaEntity.class, metadata, inits);
    }

    public QReservationJpaEntity(Class<? extends ReservationJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.screening = inits.isInitialized("screening") ? new project.redis.infrastructure.screening.entity.QScreeningJpaEntity(forProperty("screening"), inits.get("screening")) : null;
    }

}

