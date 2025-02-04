package hanghae.domain.port;

import hanghae.domain.type.AgeRating;
import hanghae.domain.type.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MovieScheduleScreenDto(
        Long movieId,
        String movieTitle,
        AgeRating ageRating,
        LocalDate releaseDate,
        String thumbnailUrl,
        int runningTime,
        Genre genre,

        Long scheduleId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,

        Long screenId,
        String screenName
) {}
