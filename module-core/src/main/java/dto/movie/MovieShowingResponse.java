package dto.movie;

import java.util.ArrayList;
import java.util.List;

import dto.showing.ShowingResponse;
import lombok.Getter;

@Getter
public class MovieShowingResponse {
	private MovieResponse movie;
	private final List<ShowingResponse> showings = new ArrayList<>();
}
