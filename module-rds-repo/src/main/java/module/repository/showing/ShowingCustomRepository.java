package module.repository.showing;

import java.util.List;

import dto.movie.MovieShowingResponse;

public interface ShowingCustomRepository {
	List<MovieShowingResponse> getShowingByMovieTitleAndGenre(String title, Long genreId);
}
