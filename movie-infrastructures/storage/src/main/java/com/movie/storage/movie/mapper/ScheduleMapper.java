package com.movie.storage.movie.mapper;

import com.movie.domain.movie.domain.Schedule;
import com.movie.storage.movie.entity.ScheduleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleMapper {

    public static Schedule from(ScheduleEntity scheduleEntity) {
        return Schedule.of(
                scheduleEntity.getId(),
                TheaterMapper.from(scheduleEntity.getTheater()),
                ScreenMapper.from(scheduleEntity.getScreen()),
                MovieMapper.from(scheduleEntity.getMovie()),
                scheduleEntity.getStartTime(),
                scheduleEntity.getEndTime()
        );
    }

}
