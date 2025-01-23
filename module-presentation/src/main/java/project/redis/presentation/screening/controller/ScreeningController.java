package project.redis.presentation.screening.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.redis.application.screening.port.inbound.ScreeningQueryUseCase;
import project.redis.application.screening.port.inbound.ScreeningsQueryParam;
import project.redis.domain.screening.Screening;
import project.redis.presentation.screening.dto.request.ScreeningsQueryRequest;
import project.redis.presentation.screening.dto.response.GroupedScreeningResponse;
import project.redis.presentation.screening.mapper.ScreeningAppMapper;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningQueryUseCase screeningQueryUseCase;

    @GetMapping("/v1/screenings")
    public ResponseEntity<List<GroupedScreeningResponse>> getScreenings(ScreeningsQueryRequest request) {

        List<Screening> screenings = screeningQueryUseCase.getScreenings(
                ScreeningsQueryParam.builder()
                        .maxScreeningDay(request.getMaxScreeningDay())
                        .genreName(request.getGenreName())
                        .movieName(request.getMovieName())
                        .build()
        );

        return ResponseEntity.ok(ScreeningAppMapper.toGroupedScreeningResponse(screenings));
    }

    @GetMapping("/v2/screenings/local-caching")
    public ResponseEntity<List<GroupedScreeningResponse>> getScreeningsLocalCaching(ScreeningsQueryRequest request) {
        List<Screening> screenings = screeningQueryUseCase.getScreenings(
                ScreeningsQueryParam.builder()
                        .maxScreeningDay(request.getMaxScreeningDay())
                        .genreName(request.getGenreName())
                        .movieName(request.getMovieName())
                        .build()
        );

        return ResponseEntity.ok(ScreeningAppMapper.toGroupedScreeningResponse(screenings));
    }

    @GetMapping("/v3/screenings/redis")
    public ResponseEntity<List<GroupedScreeningResponse>> getScreeningsRedis(ScreeningsQueryRequest request) {
        List<Screening> screenings = screeningQueryUseCase.getScreeningsRedis(
                ScreeningsQueryParam.builder()
                        .maxScreeningDay(request.getMaxScreeningDay())
                        .genreName(request.getGenreName())
                        .movieName(request.getMovieName())
                        .build()
        );

        return ResponseEntity.ok(ScreeningAppMapper.toGroupedScreeningResponse(screenings));
    }
}
