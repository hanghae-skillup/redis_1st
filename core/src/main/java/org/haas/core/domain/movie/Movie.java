package org.haas.core.domain.movie;

import lombok.*;
import org.haas.core.domain.genre.Genre;
import org.haas.core.domain.screening.Screening;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Movie {

    private Long id;

    private String title;

    private Genre genre;

    private int runningTime;

    private String director;

    private String filmRating;

    private LocalDate releasedAt;

    private String thumbnailUrl;

    private MovieStatus movieStatus;

    private List<Screening> screenings;

    public boolean isValidScreeningDate(LocalDate screeningDate) {
        return !screeningDate.isBefore(this.releasedAt);
    }

}
