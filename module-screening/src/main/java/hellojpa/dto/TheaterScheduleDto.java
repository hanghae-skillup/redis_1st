package hellojpa.dto;

import hellojpa.domain.Screening;
import hellojpa.domain.Theater;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterScheduleDto {

    private String title; // 영화 제목
    private String name; // 상영관 이름
    private List<TimeScheduleDto> timeScheduleDtoList;
}