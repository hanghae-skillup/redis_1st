package module.controller;

import java.util.List;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.external.MovieShowingResponse;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import module.service.ShowingService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/showings")
public class ShowingController {

	private final ShowingService showingService;

	@GetMapping("/all")
	public ResponseEntity<List<MovieShowingResponse>> getTodayShowing(
		@Valid @Size(max = 10, message = "제목 최대 길이는 255자 이내 입니다.")
		@Nullable @RequestParam String title,
		@Valid @Range(min = 2, max = 10, message = "존재하지 않는 장르 입니다.")
		@Nullable @RequestParam Long genreId
	) {
		List<MovieShowingResponse> allShowingList = showingService.getTodayShowing();
		if (allShowingList.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(allShowingList);
		}
	}

}
