package project.redis.infrastructure.reservation.entity;


import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import project.redis.infrastructure.common.entity.BaseJpaEntity;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;
import project.redis.infrastructure.seat.entity.SeatJpaEntity;

@Entity
@Builder
@Table(name = "reservation_seat", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_screening_seat",
                columnNames = {"screening_id", "seat_id"}
        )
})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSeatJpaEntity extends BaseJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "reservation_seat_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ReservationJpaEntity reservation;

    @ManyToOne
    @JoinColumn(name = "screening_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ScreeningJpaEntity screening;

    @ManyToOne
    @JoinColumn(name = "seat_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SeatJpaEntity seat;
}
