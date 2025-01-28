package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.domain.model.Movie;
import com.hanghae.domain.model.Screen;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.infrastructure.entity.ScreeningScheduleEntity;
import com.hanghae.infrastructure.mapper.ScreeningScheduleMapper;
import com.hanghae.infrastructure.mapper.UploadFileMapper;
import com.hanghae.infrastructure.repository.ScreeningScheduleRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScreeningScheduleRepositoryAdapter implements ScreeningScheduleRepositoryPort {
    private final ScreeningScheduleRepositoryJpa repository;

    @Override
    public List<ScreeningSchedule> findAll() {
        return repository.findAll().stream()
                .map(ScreeningScheduleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ScreeningSchedule findById(Long id) {
        return ScreeningScheduleMapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상영시간표를 찾을 수 없습니다.")));
    }
}