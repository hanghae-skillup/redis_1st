package module.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dto.movie.MovieShowingResponse;
import module.service.showing.ShowingService;

@SpringBootTest
class ShowingServiceTest {

	ShowingService showingService;
	ModelMapper modelMapper;

	@Autowired
	public ShowingServiceTest(ShowingService showingService, ModelMapper modelMapper) {
		this.showingService = showingService;
		this.modelMapper = modelMapper;
	}

	@Test
	@DisplayName("injection Test")
	public void injectionTest() {
		assertNotNull(showingService);
	}

	@Test
	@DisplayName("영화 정보 요구사항 테스트 ")
	public void getTodayShowingTest() {
		//given
		List<MovieShowingResponse> todayShowing = showingService.getTodayShowing(null, null);

		//when
		MovieShowingResponse movieShowingResponse = todayShowing.getFirst();

		//then
		assertTrue(movieShowingResponse.getShowings().get(0).getShowStTime().isAfter(LocalDateTime.now()));
	}

}
