package module.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.external.MovieShowingResponse;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import module.service.ShowingService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/showings")
public class ShowingController {

	private final ShowingService showingService;

	@GetMapping("/all")
	public ResponseEntity<Map> getTodayShowing(@Nullable @RequestParam String title,@Nullable @RequestParam Long genre) {
		List<MovieShowingResponse> allShowingList = showingService.getTodayShowing();
		if (allShowingList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Map.of("data", allShowingList));
		}
	}
}
