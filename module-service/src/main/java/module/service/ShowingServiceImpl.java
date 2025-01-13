package module.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.external.MovieShowingResponse;
import dto.internal.ShowingDTO;
import lombok.RequiredArgsConstructor;
import module.entity.Showing;
import module.repository.ShowingRepository;

@Service
@RequiredArgsConstructor
public class ShowingServiceImpl implements ShowingService {

	private final ShowingRepository showingRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<MovieShowingResponse> getTodayShowing() {
		LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		List<Showing> showings = showingRepository.findShowingsByStTime(today); // 금일 이후로 상영하는 모든 상영정보

		Map<Long, List<ShowingDTO>> groupedShowings = groupShowingsByMovie(showings);

		return groupedShowings.entrySet().stream()
			.map(entry -> createMovieShowingResponse(entry.getValue()))
			.collect(Collectors.toList());
	}

	private Map<Long, List<ShowingDTO>> groupShowingsByMovie(List<Showing> showings) {
		return showings.stream()
			.map(showing -> modelMapper.map(showing, ShowingDTO.class))
			.collect(Collectors.groupingBy(showingDTO -> showingDTO.getMovie().getId()));
	}

	private MovieShowingResponse createMovieShowingResponse(List<ShowingDTO> showingDTOs) {
		MovieShowingResponse response = new MovieShowingResponse();
		response.setMovie(showingDTOs.get(0).getMovie()); // 모든 DTO가 같은 영화 정보를 가짐
		showingDTOs.forEach(response::addShowing);
		return response;
	}

}
