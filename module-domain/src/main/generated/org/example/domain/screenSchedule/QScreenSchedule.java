package org.example.domain.screenSchedule;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScreenSchedule is a Querydsl query type for ScreenSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreenSchedule extends EntityPathBase<ScreenSchedule> {

    private static final long serialVersionUID = 943941266L;

    public static final QScreenSchedule screenSchedule = new QScreenSchedule("screenSchedule");

    public final org.example.entity.QBaseEntity _super = new org.example.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> movieId = createNumber("movieId", Long.class);

    public final NumberPath<Long> screenRoomId = createNumber("screenRoomId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QScreenSchedule(String variable) {
        super(ScreenSchedule.class, forVariable(variable));
    }

    public QScreenSchedule(Path<? extends ScreenSchedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScreenSchedule(PathMetadata metadata) {
        super(ScreenSchedule.class, metadata);
    }

}

