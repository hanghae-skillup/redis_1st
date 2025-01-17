package hellojpa.dto;

import hellojpa.domain.Movie;
import hellojpa.domain.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeScheduleDto {

    private LocalTime startTime;
    private LocalTime endTime;

    public static TimeScheduleDto of(Screening screening) {
        LocalTime startTime = screening.getStartTime();
        LocalTime endTime = startTime.plusMinutes(screening.getMovie().getRunningTime());

        return new TimeScheduleDto(startTime, endTime);
    }
}