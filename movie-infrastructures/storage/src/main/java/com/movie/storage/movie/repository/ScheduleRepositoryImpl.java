package com.movie.storage.movie.repository;

import com.movie.domain.movie.ScheduleRepository;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import com.movie.storage.movie.dto.payload.SchedulePayload;
import com.movie.storage.movie.dto.statement.ScheduleStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaQuerySupport scheduleJpaQuerySupport;

    @Override
    public List<ScheduleInfo.Get> getSchedules(Long theaterId) {
        List<SchedulePayload.Get> schedulePayLoads = scheduleJpaQuerySupport.getSchedules(theaterId);
        return schedulePayLoads.stream().map(SchedulePayload.Get::to).toList();
    }

    @Override
    public List<ScheduleInfo.Get> getSchedules(ScheduleCommand.Search search) {
        ScheduleStatement.Search scheduleSearch =
                ScheduleStatement.Search.of(search.movieName(), search.genre());
        List<SchedulePayload.Get> schedules = scheduleJpaQuerySupport.getSchedules(scheduleSearch);
        return schedules.stream().map(SchedulePayload.Get::to).toList();
    }

}
