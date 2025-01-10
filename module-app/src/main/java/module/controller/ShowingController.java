package module.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.internal.MovieDTO;
import dto.internal.ShowingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import dto.external.ShowingResponse;
import module.service.ShowingService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/showing")
public class ShowingController {

	private final ShowingService showingService;
	private final ModelMapper modelMapper;

	@GetMapping("/today-info")
	public ResponseEntity<Map> getTodayShowing() {
		List<Map.Entry<MovieDTO, List<ShowingDTO>>> todayShowing = showingService.getTodayShowing();

		List<Map<String, Object>> responseData = todayShowing.stream()
			.map(entry -> Map.entry(entry.getKey(), entry.getValue().stream()
				.map(val -> modelMapper.map(val, ShowingResponse.class)).toList()))
			.map(entry -> Map.of("movie", entry.getKey(), "timeTable", entry.getValue()))
			.toList();

		if (responseData.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(Map.of("data", responseData));
		}
	}
}
