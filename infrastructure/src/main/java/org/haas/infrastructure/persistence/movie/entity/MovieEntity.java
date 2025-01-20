package org.haas.infrastructure.persistence.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.movie.MovieStatus;

import java.time.LocalDate;

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

    private String genre;

    private int runningTime;

    private String director;

    private String filmRating;

    private LocalDate releasedAt;

    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;


}
