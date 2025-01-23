package project.redis.presentation.cinema.mapper;

import project.redis.domain.cinema.Cinema;
import project.redis.presentation.cinema.dto.response.CinemaResponse;

public class CinemaApiMapper {

    public static CinemaResponse toCinemaResponse(Cinema cinema) {
        return CinemaResponse.builder()
                .cinemaId(cinema.getCinemaId())
                .cinemaName(cinema.getCinemaName())
                .build();
    }

}
