package hellojpa.dto;

import hellojpa.domain.Movie;
import hellojpa.domain.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeScheduleDto {

    private LocalTime startTime;
    private LocalTime endTime;

    public TimeScheduleDto(LocalTime startTime, int runningTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(runningTime);  // endTime 계산
    }
}