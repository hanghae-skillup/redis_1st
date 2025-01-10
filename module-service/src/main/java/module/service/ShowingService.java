package module.service;

import java.util.List;
import java.util.Map;

import dto.MovieDTO;
import dto.ShowingDTO;

public interface ShowingService {
	List<Map<MovieDTO, List<ShowingDTO>>> getTodayShowing();
}
