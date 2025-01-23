package project.redis.infrastructure.genre.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenreJpaEntity is a Querydsl query type for GenreJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGenreJpaEntity extends EntityPathBase<GenreJpaEntity> {

    private static final long serialVersionUID = 515269496L;

    public static final QGenreJpaEntity genreJpaEntity = new QGenreJpaEntity("genreJpaEntity");

    public final project.redis.infrastructure.common.entity.QBaseJpaEntity _super = new project.redis.infrastructure.common.entity.QBaseJpaEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final ComparablePath<java.util.UUID> createdBy = _super.createdBy;

    public final StringPath genreName = createString("genreName");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final ComparablePath<java.util.UUID> updatedBy = _super.updatedBy;

    public QGenreJpaEntity(String variable) {
        super(GenreJpaEntity.class, forVariable(variable));
    }

    public QGenreJpaEntity(Path<? extends GenreJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenreJpaEntity(PathMetadata metadata) {
        super(GenreJpaEntity.class, metadata);
    }

}

