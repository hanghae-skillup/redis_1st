package module.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dto.MovieDTO;
import dto.ShowingDTO;

@SpringBootTest
class ShowingServiceImplTest {

	ShowingService showingService;
	ModelMapper modelMapper;

	@Autowired
	public ShowingServiceImplTest(ShowingService showingService, ModelMapper modelMapper) {
		this.showingService = showingService;
		this.modelMapper = modelMapper;
	}

	@Test
	@DisplayName("1. injection Test")
	public void injectionTest() {
		assertNotNull(showingService);
	}

	@Test
	@DisplayName("2. 영화 정보 요구사항 테스트 ")
	public void getTodayShowingTest() {
		//given
		List<Map<MovieDTO, List<ShowingDTO>>> todayShowing = showingService.getTodayShowing();

		//when
		Map<MovieDTO, List<ShowingDTO>> movieDTOListMap = todayShowing.get(0);

		//then
		List<ShowingDTO> showingDTOS = movieDTOListMap.values().stream().findFirst().get();

		assertEquals(movieDTOListMap.values().size(),1);
		assertEquals(showingDTOS.getFirst().getStTime().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
		assertEquals(showingDTOS.getLast().getStTime().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
	}

}
