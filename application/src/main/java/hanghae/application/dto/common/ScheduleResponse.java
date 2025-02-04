package hanghae.application.dto.common;

import hanghae.domain.entity.Schedule;
import hanghae.domain.port.MovieScheduleScreenDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ScheduleResponse(
        String startTime,
        String endTime
) {

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                getFormattedDateTime(schedule.getStartDateTime()),
                getFormattedDateTime(schedule.getEndDateTime())
        );
    }

    public static ScheduleResponse of(MovieScheduleScreenDto movieScheduleScreenDto) {
        return new ScheduleResponse(
                getFormattedDateTime(movieScheduleScreenDto.startDateTime()),
                getFormattedDateTime(movieScheduleScreenDto.endDateTime())
        );
    }

    private static String getFormattedDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
