package dto.internal;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowingDTO {
	private Long id;
	private MovieDTO movie;
	private ScreenDTO screen;
	private LocalDateTime stTime;
	private LocalDateTime edTime;
}
