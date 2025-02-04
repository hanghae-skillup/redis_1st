package project.redis.infrastructure.screening.mapper;


import project.redis.domain.screening.Screening;
import project.redis.infrastructure.cinema.mapper.CinemaInfraMapper;
import project.redis.infrastructure.movie.mapper.MovieInfraMapper;
import project.redis.infrastructure.screening.entity.ScreeningJpaEntity;

public class ScreeningInfraMapper {

    public static Screening toScreening(ScreeningJpaEntity screening) {
        return Screening.generateScreening(
                screening.getId(),
                screening.getScreeningStartTime(),
                screening.getScreeningEndTime(),
                MovieInfraMapper.toMovie(screening.getMovie()),
                CinemaInfraMapper.toCinema(screening.getCinema())
        );
    }

    public static ScreeningJpaEntity toEntity(Screening screening) {
        return ScreeningJpaEntity.builder()
                .id(screening.getScreeningId())
                .screeningEndTime(screening.getScreenEndTime())
                .screeningStartTime(screening.getScreenStartTime())
                .movie(MovieInfraMapper.toEntity(screening.getMovie()))
                .cinema(CinemaInfraMapper.toEntity(screening.getCinema()))
                .build();
    }

}
