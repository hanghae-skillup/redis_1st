package org.haas.core.domain.movie;

import lombok.*;
import org.haas.core.domain.genre.Genre;
import org.haas.core.domain.screening.Screening;
import org.haas.core.domain.theater.Theater;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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

    /**
     * @param screeningDate
     * @return
     * 상영일(screeningDate)이 개봉일 보다 이전이면 false
     */
    public boolean isValidScreeningDate(LocalDate screeningDate) {
        return !screeningDate.isBefore(this.releasedAt);
    }


}
