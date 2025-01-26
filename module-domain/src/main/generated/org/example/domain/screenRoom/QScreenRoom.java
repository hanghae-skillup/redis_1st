package org.example.domain.screenRoom;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScreenRoom is a Querydsl query type for ScreenRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreenRoom extends EntityPathBase<ScreenRoom> {

    private static final long serialVersionUID = -1107225198L;

    public static final QScreenRoom screenRoom = new QScreenRoom("screenRoom");

    public final org.example.entity.QBaseEntity _super = new org.example.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QScreenRoom(String variable) {
        super(ScreenRoom.class, forVariable(variable));
    }

    public QScreenRoom(Path<? extends ScreenRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScreenRoom(PathMetadata metadata) {
        super(ScreenRoom.class, metadata);
    }

}

