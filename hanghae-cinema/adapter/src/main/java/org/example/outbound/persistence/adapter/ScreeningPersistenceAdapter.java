package org.example.outbound.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.domain.Movie;
import org.example.domain.Screening;
import org.example.domain.ScreeningSchedule;
import org.example.outbound.persistence.entity.ScreeningJpaEntity;
import org.example.outbound.persistence.repository.ScreeningRepository;
import org.example.port.inbound.ScreeningPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
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
                            .id(entity.getMovieJpaEntity().getId())
                            .title(entity.getMovieJpaEntity().getTitle())
                            .genre(entity.getMovieJpaEntity().getGenre())
                            .releaseDate(entity.getMovieJpaEntity().getReleaseDate())
                            .runtimeMinutes(entity.getMovieJpaEntity().getRuntimeMinutes())
                            .ageRating(entity.getMovieJpaEntity().getAgeRating())
                            .thumbnailUrl(entity.getMovieJpaEntity().getThumbnailUrl())
                            .createdAt(entity.getMovieJpaEntity().getCreatedAt())
                            .createdBy(entity.getMovieJpaEntity().getCreatedBy())
                            .updatedAt(entity.getMovieJpaEntity().getUpdatedAt())
                            .updatedBy(entity.getMovieJpaEntity().getUpdatedBy())
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
