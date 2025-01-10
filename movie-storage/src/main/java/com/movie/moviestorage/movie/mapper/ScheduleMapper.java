package com.movie.moviestorage.movie.mapper;

import com.movie.moviedomain.movie.domain.Schedule;
import com.movie.moviestorage.movie.entity.ScheduleEntity;
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
