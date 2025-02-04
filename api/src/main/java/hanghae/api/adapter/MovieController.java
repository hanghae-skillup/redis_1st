package hanghae.api.adapter;

import hanghae.application.dto.response.MoviePlayingResponse;
import hanghae.application.port.MovieService;
import hanghae.domain.type.Genre;
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
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MoviePlayingResponse>> findMoviesPlayingWithFilters(
            @RequestParam(required = false) @Size(max = 50) String title,
            @RequestParam(required = false) Genre genre
            ) {

        return ResponseEntity.ok(movieService.findMoviesPlayingWithFilters(title, genre));
    }

}
