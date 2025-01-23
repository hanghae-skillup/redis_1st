package project.redis.infrastructure.movie.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieJpaEntity is a Querydsl query type for MovieJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieJpaEntity extends EntityPathBase<MovieJpaEntity> {

    private static final long serialVersionUID = 1818379864L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieJpaEntity movieJpaEntity = new QMovieJpaEntity("movieJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final project.redis.infrastructure.genre.entity.QGenreJpaEntity genre;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final EnumPath<project.redis.domain.movie.RatingClassification> rating = createEnum("rating", project.redis.domain.movie.RatingClassification.class);

    public final DatePath<java.time.LocalDate> releaseDate = createDate("releaseDate", java.time.LocalDate.class);

    public final NumberPath<Integer> runningMinTime = createNumber("runningMinTime", Integer.class);

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QMovieJpaEntity(String variable) {
        this(MovieJpaEntity.class, forVariable(variable), INITS);
    }

    public QMovieJpaEntity(Path<? extends MovieJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieJpaEntity(PathMetadata metadata, PathInits inits) {
        this(MovieJpaEntity.class, metadata, inits);
    }

    public QMovieJpaEntity(Class<? extends MovieJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.genre = inits.isInitialized("genre") ? new project.redis.infrastructure.genre.entity.QGenreJpaEntity(forProperty("genre")) : null;
    }

}

