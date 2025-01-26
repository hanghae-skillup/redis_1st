package dto.showing;

import java.time.LocalDateTime;

import dto.movie.MovieDTO;
import dto.screen.ScreenDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowingDTO {
	private Long showingId;
	private MovieDTO movie;
	private ScreenDTO screen;
	private LocalDateTime stTime;
	private LocalDateTime edTime;
}
