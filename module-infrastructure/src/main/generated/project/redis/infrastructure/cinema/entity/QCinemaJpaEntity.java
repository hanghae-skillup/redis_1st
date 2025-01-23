package project.redis.infrastructure.cinema.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCinemaJpaEntity is a Querydsl query type for CinemaJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCinemaJpaEntity extends EntityPathBase<CinemaJpaEntity> {

    private static final long serialVersionUID = -1437267300L;

    public static final QCinemaJpaEntity cinemaJpaEntity = new QCinemaJpaEntity("cinemaJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    public final StringPath cinemaName = createString("cinemaName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QCinemaJpaEntity(String variable) {
        super(CinemaJpaEntity.class, forVariable(variable));
    }

    public QCinemaJpaEntity(Path<? extends CinemaJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCinemaJpaEntity(PathMetadata metadata) {
        super(CinemaJpaEntity.class, metadata);
    }

}

