package org.haas.infrastructure.persistence.screening.mapper;

import org.haas.core.domain.movie.Movie;
import org.haas.core.domain.screening.Screening;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.haas.infrastructure.persistence.screening.entity.ScreeningEntity;
import org.springframework.stereotype.Component;

@Component
public class ScreeningMapper {

    public Screening toDomain(ScreeningEntity screeningEntity) {
        return Screening.builder()
                .screeningId(screeningEntity.getScreeningId())
                .startTime(screeningEntity.getStartTime())
                .endTime(screeningEntity.getEndTime())
//                .theater(screeningEntity.getTheater())
                .build();
    }

    public ScreeningEntity toEntity(Screening screening) {
        return ScreeningEntity.builder()
                .startTime(screening.getStartTime())
                .endTime(screening.getEndTime())
//                .theater(screening.getTheater())
                .build();
    }

}
