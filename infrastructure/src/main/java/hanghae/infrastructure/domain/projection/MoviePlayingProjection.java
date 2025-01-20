package hanghae.infrastructure.domain.projection;

import hanghae.domain.types.movie.AgeRating;
import hanghae.domain.types.movie.Genre;
import hanghae.domain.types.movie.RunningTime;
import hanghae.domain.types.showtime.Schedule;

import java.time.LocalDate;

// TODO projection 으로 리팩토링 예정
public interface MoviePlayingProjection {

    String getTitle();
    AgeRating getAgeRating();
    LocalDate getReleaseDate();
    String getThumbnailUrl();
    RunningTime getRunningTime();
    Genre getGenre();

    Schedule getSchedule();

    String getTheaterName();
}
