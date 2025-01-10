package module.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.internal.MovieDTO;
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
	public List<Map.Entry<MovieDTO, List<ShowingDTO>>> getTodayShowing() {
		LocalDateTime from = LocalDateTime.now().plusMinutes(30);
		LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

		List<Showing> showingsByStTimeBetween = showingRepository.findShowingsByStTimeBetween(from, to);

		// 금일 상영 정보가 아예 없을 경우 다음날 조회
		if(showingsByStTimeBetween.isEmpty()){
			LocalDateTime tomorrowFrom = LocalDateTime.of(LocalDate.now().plusDays(1L), LocalTime.MIN);
			LocalDateTime tomorrowTo = to.plusDays(1L);
			showingsByStTimeBetween = showingRepository.findShowingsByStTimeBetween(tomorrowFrom, tomorrowTo);
		}

		return showingsByStTimeBetween.stream()
			.map(showingEntity -> modelMapper.map(showingEntity, ShowingDTO.class))
			.collect(Collectors.groupingBy(ShowingDTO::getMovie))
			.entrySet().stream()
			.peek(entry -> entry.getValue().sort(Comparator.comparing(ShowingDTO::getStTime)))
			.sorted(Map.Entry.comparingByKey(Comparator.comparing(MovieDTO::getOpenDay, Comparator.reverseOrder())))
			.toList();
	}
}
