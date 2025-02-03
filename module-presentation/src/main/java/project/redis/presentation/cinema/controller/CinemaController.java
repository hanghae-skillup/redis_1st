package project.redis.presentation.cinema.controller;


import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.redis.application.cinema.port.inbound.CinemaCommandUseCase;
import project.redis.application.cinema.port.inbound.CinemaCreateCommandParam;
import project.redis.application.cinema.port.inbound.CinemaQueryUseCase;
import project.redis.domain.cinema.Cinema;
import project.redis.presentation.cinema.dto.request.CinemaCreateRequest;
import project.redis.presentation.cinema.dto.response.CinemaResponse;
import project.redis.presentation.cinema.mapper.CinemaApiMapper;

@RestController
@RequestMapping("/api/v1/cinemas")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaQueryUseCase cinemaQueryUseCase;
    private final CinemaCommandUseCase cinemaCommandUseCase;

    @GetMapping
    public ResponseEntity<List<CinemaResponse>> getCinemas() {
        List<Cinema> cinemas = cinemaQueryUseCase.getCinemas();

        List<CinemaResponse> result = cinemas.stream()
                .map(CinemaApiMapper::toCinemaResponse)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> createCinema(@RequestBody @Valid CinemaCreateRequest request) {
        cinemaCommandUseCase.createCinema(
                CinemaCreateCommandParam.builder()
                        .CinemaName(request.getCinemaName())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
