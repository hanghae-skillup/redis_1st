package module.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.external.MovieShowingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.service.ShowingService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/showing")
public class ShowingController {

	private final ShowingService showingService;
	private final ModelMapper modelMapper;

	@GetMapping("/all")
	public ResponseEntity<Map> getTodayShowing() {
		List<MovieShowingResponse> allShowingList = showingService.getTodayShowing();

		if (allShowingList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Map.of("data", allShowingList));
		}
	}
}
