package com.movie.domain.movie.dto.info;

import com.movie.domain.movie.domain.TimeTable;

import java.time.LocalDateTime;

public class TimeTableInfo {

    public record Get(LocalDateTime startDate, LocalDateTime endDate) {
        public static Get of(LocalDateTime startDate, LocalDateTime endDate) {
            return new Get(startDate, endDate);
        }
        public static Get from(TimeTable timeTable) {
            return Get.of(timeTable.getStartTime(), timeTable.getEndTime());
        }
    }

}
