package project.redis.infrastructure.seat.entity;


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
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;
import project.redis.infrastructure.common.entity.BaseJpaEntity;

@Entity
@Builder
@Table(name = "seat")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "seat_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", columnDefinition = "BINARY(16)",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private CinemaJpaEntity cinema;
}
