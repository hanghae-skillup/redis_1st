package project.redis.infrastructure.movie.entity;


import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import project.redis.infrastructure.common.entity.BaseJpaEntity;
import project.redis.domain.movie.RatingClassification;
import project.redis.infrastructure.genre.entity.GenreJpaEntity;

@Entity
@Builder
@Table(name = "movie")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieJpaEntity extends BaseJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "movie_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private RatingClassification rating;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(nullable = false)
    private int runningMinTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", columnDefinition = "BINARY(16)",
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private GenreJpaEntity genre;
}
