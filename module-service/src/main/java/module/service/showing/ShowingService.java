package module.service.showing;

import java.util.List;

import dto.movie.MovieShowingResponse;

public interface ShowingService {
	List<MovieShowingResponse> getTodayShowing(String title, Long genreId);
}
