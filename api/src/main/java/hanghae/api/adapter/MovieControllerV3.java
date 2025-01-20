package hanghae.api.adapter;

import hanghae.application.dto.MovieResponse;
import hanghae.application.port.MovieServiceV3;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/movie")
public class MovieControllerV3 {

    private final MovieServiceV3 movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMoviesPlaying(
            @RequestParam(required = false) @Size(max = 50) String title,
            @RequestParam(required = false) @Size(max = 50) String genre
    ) {
        return ResponseEntity.ok(movieService.findMoviesPlayingWithFilters(title, genre));
    }
}
