package org.haas.core.domain.screening;

import lombok.*;
import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.theater.Theater;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Screening {

    private Long screeningId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Theater theater;

    private Movie movie;
}
