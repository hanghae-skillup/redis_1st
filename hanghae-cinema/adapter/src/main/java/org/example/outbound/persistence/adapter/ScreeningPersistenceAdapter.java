package org.example.outbound.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.domain.Movie;
import org.example.domain.Screening;
import org.example.domain.ScreeningSchedule;
import org.example.outbound.persistence.entity.ScreeningJpaEntity;
import org.example.outbound.persistence.repository.ScreeningRepository;
import org.example.port.inbound.ScreeningPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScreeningPersistenceAdapter implements ScreeningPort {

    private final ScreeningRepository screeningRepository;

    @Override
    public List<Screening> findAllScreeningByShowDateAfter(LocalDate date) {
        List<ScreeningJpaEntity> entityList = screeningRepository.findAllScreeningByShowDateAfter(date);
        return entityList.stream()
                .map(entity -> {
                    Movie movie = Movie.builder()
                            .id(entity.getMovie().getId())
                            .title(entity.getMovie().getTitle())
                            .genre(entity.getMovie().getGenre())
                            .releaseDate(entity.getMovie().getReleaseDate())
                            .runtimeMinutes(entity.getMovie().getRuntimeMinutes())
                            .ageRating(entity.getMovie().getAgeRating())
                            .thumbnailUrl(entity.getMovie().getThumbnailUrl())
                            .createdAt(entity.getMovie().getCreatedAt())
                            .createdBy(entity.getMovie().getCreatedBy())
                            .updatedAt(entity.getMovie().getUpdatedAt())
                            .updatedBy(entity.getMovie().getUpdatedBy())
                            .build();
                    List<ScreeningSchedule> screeningSchedules = entity.getSchedules()
                            .stream()
                            .map(scheduleEntity -> ScreeningSchedule.builder()
                                    .id(scheduleEntity.getId())
                                    .startTime(scheduleEntity.getStartTime())
                                    .endTime(scheduleEntity.getEndTime())
                                    .createdAt(scheduleEntity.getCreatedAt())
                                    .updatedAt(scheduleEntity.getUpdatedAt())
                                    .createdBy(scheduleEntity.getCreatedBy())
                                    .updatedBy(scheduleEntity.getUpdatedBy())
                                    .build())
                            .toList();
                    return Screening.builder()
                            .id(entity.getId())
                            .movie(movie)
                            .theater(entity.getTheater())
                            .showDate(entity.getShowDate())
                            .schedules(screeningSchedules)
                            .createdAt(entity.getCreatedAt())
                            .updatedAt(entity.getUpdatedAt())
                            .createdBy(entity.getCreatedBy())
                            .updatedBy(entity.getUpdatedBy())
                            .build();
                })
                .toList();
    }
}
