package dto.showing;

import java.time.LocalDateTime;

import dto.screen.ScreenResponse;
import lombok.Getter;

@Getter
public class ShowingResponse {
	private Long showingId;
	private LocalDateTime showStTime;
	private LocalDateTime showEdTime;
	private ScreenResponse screen;
}
