package module.service.showing;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.movie.MovieShowingResponse;
import lombok.RequiredArgsConstructor;
import module.repository.showing.ShowingRepository;

@Service
@RequiredArgsConstructor
public class ShowingServiceImpl implements ShowingService {

	private final ShowingRepository showingRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<MovieShowingResponse> getTodayShowing(String title, Long genreId) {
		return showingRepository.getShowingByMovieTitleAndGenre(title, genreId);
	}

}
