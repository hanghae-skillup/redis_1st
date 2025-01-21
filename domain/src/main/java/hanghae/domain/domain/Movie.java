package hanghae.domain.domain;

import hanghae.domain.types.movie.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Movie {

    private MovieId id;

    private String title;

    private AgeRating ageRating;

    private ReleaseDate releaseDate;

    private String thumbnailUrl;

    private RunningTime runningTime;

    private Genre genre;

    private List<Showtime> showtime;

    private List<Theater> theater;

    public LocalDate getReleaseDateAsLocalDate() {
        return releaseDate.getDate();
    }

    public int getRunningTimeAsMinutes() {
        return runningTime.getMinutes();
    }

    public Long getMovieId() {
        return this.id.getMovieId();
    }

    public Movie setShowtime(List<Showtime> showtimeList) {
        this.showtime = showtimeList;
        return this;
    }

    public Movie setTheater(List<Theater> theaterList) {
        this.theater = theaterList;
        return this;
    }
}
