package org.haas.core.domain.movie;

import lombok.*;
import org.haas.core.domain.theater.Theater;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    private Long id;

    private String title;

    private String genre;

    private int runningTime;

    private String director;

    private String filmRating;

    private LocalDate releasedAt;

    private String thumbnailUrl;

    private MovieStatus movieStatus;

}
