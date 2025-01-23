package project.redis.infrastructure.cinema.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import project.redis.infrastructure.common.entity.BaseJpaEntity;

@Entity
@Builder
@Table(name = "cinema")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CinemaJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "cinema_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String cinemaName;
}
