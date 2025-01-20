package hanghae.domain.types.showtime;

import hanghae.domain.exception.showtime.InvalidScheduleException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Schedule {

    @Getter
    private final LocalDateTime startDateTime;

    @Getter
    private final LocalDateTime endDateTime;

    private final LocalDateTime baseDateTime;

    public Schedule(LocalDateTime startDateTime, LocalDateTime endDateTime) throws InvalidScheduleException {
        this.baseDateTime = LocalDateTime.now();

        // 더미 데이터 삽입을 위해 검증 로직 일시적 비활성화
//        isValidSchedule(startDateTime, endDateTime, baseDateTime);

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private LocalDate convertStartTimeToLocalDate() {
        return startDateTime.toLocalDate();
    }

    private void isValidSchedule(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime baseTime) throws InvalidScheduleException {
        if (startTime.isBefore(baseTime) || endTime.isBefore(baseTime)) {
            throw new InvalidScheduleException(
                    String.format(
                            "schedule start time (%s) or end time (%s) " +
                                    "cannot be in the past (base time: %s).",
                            startTime, endTime, baseTime
                    )
            );
        }

        if (endTime.isBefore(startTime)) {
            throw new InvalidScheduleException("endTime can not be before startTime");
        }
    }

    public void isValidScheduleWithMovieReleaseDate(LocalDate movieReleaseDate) throws InvalidScheduleException {
        LocalDate startDate = convertStartTimeToLocalDate();

        if (startDate.isBefore(movieReleaseDate)) {
            throw new InvalidScheduleException(
                    "schedule start date can not be before movie release date"
            );
        }
    }

    public void isValidScheduleWithMovieRunningTime(int movieRunningTime) throws InvalidScheduleException {
        LocalDateTime calculatedEndTime = startDateTime.plusMinutes(movieRunningTime);

        if (endDateTime.isBefore(calculatedEndTime)) {
            throw new InvalidScheduleException(
                    String.format("schedule end time (%s) is before calculated end time (%s).",
                            endDateTime, calculatedEndTime
                    )
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(startDateTime, schedule.startDateTime) && Objects.equals(endDateTime, schedule.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "startTime=" + startDateTime +
                ", endTime=" + endDateTime +
                ", baseTime=" + baseDateTime +
                '}';
    }
}
