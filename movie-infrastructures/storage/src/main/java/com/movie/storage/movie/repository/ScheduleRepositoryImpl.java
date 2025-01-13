package com.movie.storage.movie.repository;

import com.movie.moviedomain.movie.ScheduleRepository;
import com.movie.moviedomain.movie.domain.Schedule;
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
    private final TheaterJpaRepository theaterJpaRepository;

    @Override
    public List<Schedule> getSchedules(Long theaterId) {
        TheaterEntity theaterEntity = theaterJpaRepository.findById(theaterId).orElseThrow(
                () -> new EntityNotFoundException("no theater found : id - %d".formatted(theaterId)));

        return scheduleJpaRepository.findByTheater(theaterEntity).stream()
                .map(ScheduleMapper::from)
                .toList();
    }
}
