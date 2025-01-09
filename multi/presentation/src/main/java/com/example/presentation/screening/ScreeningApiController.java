package com.example.presentation.screening;

import com.example.application.moviescreening.MovieScreeningFacade;
import com.example.domain.screening.Screening;
import com.example.domain.screening.service.ScreeningService;
import com.example.presentation.screening.model.ScreeningRequestDTO;
import com.example.presentation.screening.model.ScreeningResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screening")
@RequiredArgsConstructor
@Slf4j
public class ScreeningApiController {

    private final ScreeningService screeningService;
    private final MovieScreeningFacade movieScreeningFacade;

    @PostMapping("")
    public void enrollScreening(@RequestBody ScreeningRequestDTO screeningRequestDTO){
        var screening = ScreeningRequestDTO.of(screeningRequestDTO);
        movieScreeningFacade.enrollScreen(screeningRequestDTO.getMovieId(),screeningRequestDTO.getTheaterId(),screening);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllScreeningInfo(){
        var screeningInfos = screeningService.getAllScreeningInfo();
        var responseDTO = screeningInfos.stream()
                .map(ScreeningResponseDTO::of)
                .toList();
        log.info("responseDTO: {}", responseDTO);
        return ResponseEntity.ok(responseDTO);

    }
}
