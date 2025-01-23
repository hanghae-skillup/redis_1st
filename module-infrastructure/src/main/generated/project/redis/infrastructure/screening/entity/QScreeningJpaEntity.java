package project.redis.infrastructure.screening.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreeningJpaEntity is a Querydsl query type for ScreeningJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreeningJpaEntity extends EntityPathBase<ScreeningJpaEntity> {

    private static final long serialVersionUID = -456835560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreeningJpaEntity screeningJpaEntity = new QScreeningJpaEntity("screeningJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    public final project.redis.infrastructure.cinema.entity.QCinemaJpaEntity cinema;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final project.redis.infrastructure.movie.entity.QMovieJpaEntity movie;

    public final DateTimePath<java.time.LocalDateTime> screeningEndTime = createDateTime("screeningEndTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> screeningStartTime = createDateTime("screeningStartTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QScreeningJpaEntity(String variable) {
        this(ScreeningJpaEntity.class, forVariable(variable), INITS);
    }

    public QScreeningJpaEntity(Path<? extends ScreeningJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreeningJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreeningJpaEntity(PathMetadata metadata, PathInits inits) {
        this(ScreeningJpaEntity.class, metadata, inits);
    }

    public QScreeningJpaEntity(Class<? extends ScreeningJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinema = inits.isInitialized("cinema") ? new project.redis.infrastructure.cinema.entity.QCinemaJpaEntity(forProperty("cinema")) : null;
        this.movie = inits.isInitialized("movie") ? new project.redis.infrastructure.movie.entity.QMovieJpaEntity(forProperty("movie"), inits.get("movie")) : null;
    }

}

