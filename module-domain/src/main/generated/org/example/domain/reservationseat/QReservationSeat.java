package org.example.domain.reservationseat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationSeat is a Querydsl query type for ReservationSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationSeat extends EntityPathBase<ReservationSeat> {

    private static final long serialVersionUID = 652325848L;

    public static final QReservationSeat reservationSeat = new QReservationSeat("reservationSeat");

    public final org.example.entity.QBaseEntity _super = new org.example.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> reservationId = createNumber("reservationId", Long.class);

    public final NumberPath<Long> seatId = createNumber("seatId", Long.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QReservationSeat(String variable) {
        super(ReservationSeat.class, forVariable(variable));
    }

    public QReservationSeat(Path<? extends ReservationSeat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationSeat(PathMetadata metadata) {
        super(ReservationSeat.class, metadata);
    }

}

