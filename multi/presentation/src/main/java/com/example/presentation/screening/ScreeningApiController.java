package com.example.presentation.screening;

import com.example.application.moviescreening.MovieScreeningFacade;
import com.example.domain.movies.entity.enums.Genre;
import com.example.domain.screening.service.ScreeningService;
import com.example.presentation.screening.model.ScreeningRequestDTO;
import com.example.domain.screening.entity.ScreeningResponseDTO;
import jakarta.validation.constraints.Size;
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

    @GetMapping()
    public ResponseEntity<?> getAllScreeningInfo(
            @RequestParam(name = "movieName", required = false) @Size(max = 255) String movieName,
            @RequestParam(name = "genre", required = false) Genre genre
    ){
        var screeningInfos = screeningService.getAllScreeningInfo(movieName,genre);

        return ResponseEntity.ok(screeningInfos);

    }
}

