package module.service;

import java.util.List;
import java.util.Map;

import dto.internal.MovieDTO;
import dto.internal.ShowingDTO;

public interface ShowingService {
	List<Map.Entry<MovieDTO, List<ShowingDTO>>> getTodayShowing();
}
