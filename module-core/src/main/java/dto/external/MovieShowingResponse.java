package dto.external;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.internal.MovieDTO;
import dto.internal.ScreenDTO;
import dto.internal.ShowingDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieShowingResponse {
	MovieDTO movie;
	List<ShowingResponse> showings = new ArrayList<>();

	public void addShowing(ShowingDTO showingDTO){
		ShowingResponse showingResponse = new ShowingResponse();
		showingResponse.stTime = showingDTO.getStTime();
		showingResponse.edTime = showingDTO.getEdTime();
		showingResponse.screenDTO = showingDTO.getScreen();
		showings.add(showingResponse);
	}

	@Getter
	static class ShowingResponse {
		LocalDateTime stTime;
		LocalDateTime edTime;
		ScreenDTO screenDTO;
	}
}
