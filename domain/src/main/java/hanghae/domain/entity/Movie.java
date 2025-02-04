package hanghae.domain.entity;

import hanghae.domain.type.AgeRating;
import hanghae.domain.type.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
        name = "movies",
        indexes = {
                @Index(name = "idx_release_date", columnList = "release_date"),
                @Index(name = "idx_title_genre", columnList = "title, genre")
        }
)

        @NoArgsConstructor
@AllArgsConstructor
        @Getter
@Builder
        public class Movie extends Base{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long movieId;

        private String title;

        @Enumerated(EnumType.STRING)
        private AgeRating ageRating;

        private LocalDate releaseDate;

        private String thumbnailUrl;

        private int runningTime;

        @Enumerated(EnumType.STRING)
        private Genre genre;

        @Setter
        @OneToMany(
        mappedBy = "movie",
        cascade = CascadeType.ALL,
        orphanRemoval = true
)
private List<Schedule> schedules;
}
