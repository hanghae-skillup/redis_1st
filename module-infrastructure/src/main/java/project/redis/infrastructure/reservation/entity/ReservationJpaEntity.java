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
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import project.redis.infrastructure.common.entity.BaseJpaEntity;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;

@Entity
@Builder
@Table(name = "reservation")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationJpaEntity extends BaseJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "reservation_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ScreeningJpaEntity screening;
}
