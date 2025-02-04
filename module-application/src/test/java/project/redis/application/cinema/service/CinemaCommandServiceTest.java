package project.redis.application.cinema.service;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.redis.application.cinema.port.inbound.CinemaCreateCommandParam;
import project.redis.application.cinema.port.outbound.CinemaCommandPort;

@ExtendWith(MockitoExtension.class)
class CinemaCommandServiceTest {

    @Mock
    CinemaCommandPort cinemaCommandPort;

    @InjectMocks
    CinemaCommandService cinemaCommandService;


    @DisplayName("상영관 생성 - 성공")
    @Test
    void testCreateCinema() {
        String cinemaName = "cinema";
        CinemaCreateCommandParam param = new CinemaCreateCommandParam(cinemaName);

        doNothing().when(cinemaCommandPort).createCinema(param.getCinemaName());

        cinemaCommandService.createCinema(param);

        verify(cinemaCommandPort).createCinema(param.getCinemaName());
    }

    @DisplayName("상영관 생성 - 실패 - 이미 존재하는 상영관 이름")
    @Test
    void testCreateCinemaWithInvalidCinemaName() {
        String cinemaName = "cinema";
        CinemaCreateCommandParam param = new CinemaCreateCommandParam(cinemaName);

        doThrow(IllegalArgumentException.class)
                .when(cinemaCommandPort).createCinema(param.getCinemaName());

        Assertions.assertThrows(IllegalArgumentException.class, () -> cinemaCommandService.createCinema(param));

        verify(cinemaCommandPort).createCinema(param.getCinemaName());
    }

}