package module.service;

import java.util.List;

import dto.external.MovieShowingResponse;

public interface ShowingService {
	List<MovieShowingResponse> getTodayShowing();
}
