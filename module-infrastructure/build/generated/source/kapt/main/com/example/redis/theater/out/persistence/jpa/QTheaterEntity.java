package com.example.redis.theater.out.persistence.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTheaterEntity is a Querydsl query type for TheaterEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTheaterEntity extends EntityPathBase<TheaterEntity> {

    private static final long serialVersionUID = 380500209L;

    public static final QTheaterEntity theaterEntity = new QTheaterEntity("theaterEntity");

    public final com.example.redis.cmmn.QBaseEntity _super = new com.example.redis.cmmn.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final StringPath createBy = _super.createBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.example.redis.movie.out.persistence.jpa.ScreeningEntity, com.example.redis.movie.out.persistence.jpa.QScreeningEntity> screening = this.<com.example.redis.movie.out.persistence.jpa.ScreeningEntity, com.example.redis.movie.out.persistence.jpa.QScreeningEntity>createList("screening", com.example.redis.movie.out.persistence.jpa.ScreeningEntity.class, com.example.redis.movie.out.persistence.jpa.QScreeningEntity.class, PathInits.DIRECT2);

    public final ListPath<SeatEntity, QSeatEntity> seats = this.<SeatEntity, QSeatEntity>createList("seats", SeatEntity.class, QSeatEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    public QTheaterEntity(String variable) {
        super(TheaterEntity.class, forVariable(variable));
    }

    public QTheaterEntity(Path<TheaterEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTheaterEntity(PathMetadata metadata) {
        super(TheaterEntity.class, metadata);
    }

}

