package project.redis.infrastructure.screening.entity;


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
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;
import project.redis.infrastructure.common.entity.BaseJpaEntity;
import project.redis.infrastructure.movie.entity.MovieJpaEntity;

@Entity
@Builder
@Table(name = "screening")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningJpaEntity extends BaseJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "screening_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime screeningStartTime;

    @Column(nullable = false)
    private LocalDateTime screeningEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", columnDefinition = "BINARY(16)",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private MovieJpaEntity movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", columnDefinition = "BINARY(16)",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private CinemaJpaEntity cinema;
}
