package com.movie.storage.movie.repository;

import com.movie.domain.movie.ScheduleRepository;
import com.movie.domain.movie.domain.Schedule;
import com.movie.domain.movie.dto.command.ScheduleCommand;
import com.movie.domain.movie.dto.info.ScheduleInfo;
import com.movie.storage.movie.dto.payload.SchedulePayload;
import com.movie.storage.movie.dto.statement.ScheduleStatement;
import com.movie.storage.movie.entity.TheaterEntity;
import com.movie.storage.movie.mapper.ScheduleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final ScheduleJpaQuerySupport scheduleJpaQuerySupport;

    private final TheaterJpaRepository theaterJpaRepository;

    @Override
    public List<Schedule> getSchedules(Long theaterId) {
        TheaterEntity theaterEntity = theaterJpaRepository.findById(theaterId).orElseThrow(
                () -> new EntityNotFoundException("no theater found : id - %d".formatted(theaterId)));

        return scheduleJpaRepository.findByTheater(theaterEntity).stream()
                .map(ScheduleMapper::from)
                .toList();
    }

    @Override
    public List<ScheduleInfo.Get> getSchedules(ScheduleCommand.Search search) {
        ScheduleStatement.Search scheduleSearch =
                ScheduleStatement.Search.of(search.movieName(), search.genre());
        List<SchedulePayload.Get> schedules = scheduleJpaQuerySupport.getSchedules(scheduleSearch);
        return schedules.stream().map(SchedulePayload.Get::to).toList();
    }

}
