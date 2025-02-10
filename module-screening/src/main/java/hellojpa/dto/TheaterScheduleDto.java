package hellojpa.dto;

import hellojpa.domain.Screening;
import hellojpa.domain.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterScheduleDto {

    private String theaterName;
    private List<TimeScheduleDto> timeScheduleDtoList;

    public static TheaterScheduleDto of(Theater theater, List<TimeScheduleDto> timeSchedule){
        String theaterName = theater.getName();

        return new TheaterScheduleDto(
                theaterName, timeSchedule
        );
    }
}
