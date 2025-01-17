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
@RequestMapping("/api/v1/screenings")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningQueryUseCase screeningQueryUseCase;

    @GetMapping
    public ResponseEntity<List<GroupedScreeningResponse>> getScreenings(ScreeningsQueryRequest request) {

        List<Screening> screenings = screeningQueryUseCase.getScreenings(
                ScreeningsQueryParam.builder()
                        .maxScreeningDay(request.getMaxScreeningDay())
                        .build()
        );

        return ResponseEntity.ok(ScreeningAppMapper.toGroupedScreeningResponse(screenings));
    }
}
