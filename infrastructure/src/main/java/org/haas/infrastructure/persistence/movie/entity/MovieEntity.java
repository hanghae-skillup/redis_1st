package org.haas.infrastructure.persistence.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.haas.core.domain.genre.Genre;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.MovieStatus;
import org.haas.core.domain.screening.Screening;
import org.haas.infrastructure.persistence.screening.entity.ScreeningEntity;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "movies")
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Genre genre;

    private int runningTime;

    private String director;

    private String filmRating;

    private LocalDate releasedAt;

    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<ScreeningEntity> screenings;


}
