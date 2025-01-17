package project.redis.infrastructure.cinema.inbound.port;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.redis.infrastructure.cinema.entity.CinemaJpaEntity;
import project.redis.infrastructure.cinema.repository.CinemaJpaRepository;

@Component
@RequiredArgsConstructor
public class CinemaCommandAdapter implements CinemaCommandPort {

    private final CinemaJpaRepository cinemaJpaRepository;

    @Override
    public void createCinema(String cinemaName) throws IllegalArgumentException {
        Optional<CinemaJpaEntity> cinemaOptional = cinemaJpaRepository.findByCinemaName(cinemaName);

        //TODO: 예외 처리를 담당하는 Exception, ExceptionHandler를 모아두는 모듈 필요
        if (cinemaOptional.isPresent()) {
            throw new IllegalArgumentException("Cinema with name '" + cinemaName + "' already exists");
        }

        cinemaJpaRepository.save(
                CinemaJpaEntity.builder()
                        .cinemaName(cinemaName)
                        .build()
        );
    }
}
