package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.domain.model.Movie;
import com.hanghae.domain.model.Screen;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;
import com.hanghae.infrastructure.mapper.UploadFileMapper;
import com.hanghae.infrastructure.repository.ScreeningScheduleRepositoryJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScreeningScheduleRepositoryAdapter implements ScreeningScheduleRepositoryPort {

    private final ScreeningScheduleRepositoryJpa repository;

    public ScreeningScheduleRepositoryAdapter(ScreeningScheduleRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public List<ScreeningSchedule> findAll() {
        // jpa 엔티티 > 도메인 모델로 변환하여 리턴
        return repository.findAll().stream().map(entity -> new ScreeningSchedule(
                entity.getId(),
                new Movie(
                        entity.getMovieEntity().getId(),
                        entity.getMovieEntity().getTitle(),
                        entity.getMovieEntity().getRating(),
                        entity.getMovieEntity().getReleaseDate(),
                        entity.getMovieEntity().getRunningTime(),
                        entity.getMovieEntity().getGenre(),
                        UploadFileMapper.toDomain(entity.getMovieEntity().getUploadFileEntity()),
                        entity.getMovieEntity().getCreatedBy(),
                        entity.getMovieEntity().getCreatedAt(),
                        entity.getMovieEntity().getUpdatedBy(),
                        entity.getMovieEntity().getUpdatedAt()
                ),
                new Screen(
                        entity.getScreenEntity().getId(),
                        entity.getScreenEntity().getScreenName(),
                        entity.getScreenEntity().getCreatedBy(),
                        entity.getScreenEntity().getCreatedAt(),
                        entity.getScreenEntity().getUpdatedBy(),
                        entity.getScreenEntity().getUpdatedAt()
                ),
                entity.getShowStartDatetime(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedBy(),
                entity.getUpdatedAt()
        )).collect(Collectors.toList());
    }
}