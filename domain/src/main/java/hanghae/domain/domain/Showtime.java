package hanghae.domain.domain;

import hanghae.domain.types.showtime.Schedule;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
public class Showtime {

    private Schedule schedule;

    private Movie movie;

    private List<Theater> theaters;

    public Showtime(Schedule schedule, Movie movie, List<Theater> theaters) {
        this.schedule = schedule;
        this.movie = movie;

        // 더미 데이터 삽입을 위해 검증 로직 일시적 비활성화
//        schedule.isValidScheduleWithMovieReleaseDate(movie.getReleaseDateAsLocalDate());
//        schedule.isValidScheduleWithMovieRunningTime(movie.getRunningTimeAsMinutes());

        this.theaters = theaters;
    }

    public LocalDateTime getStartTime() {
        return schedule.getStartDateTime();
    }

    public String getStartTimeAsString() {
        return getFormattedLocalDateTime(getStartTime());
    }

    public LocalDateTime getEndTime() {
        return schedule.getEndDateTime();
    }

    public String getEndTimeAsString() {
        return getFormattedLocalDateTime(getEndTime());
    }

    private String getFormattedLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd:HH:mm");

        return localDateTime.format(formatter);
    }

    public List<String> getTheatersName() {
        return theaters.stream()
                .map(Theater::getTheaterName)
                .toList();
    }
}
