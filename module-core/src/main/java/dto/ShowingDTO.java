package dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShowingDTO {
	private Long id;
	private MovieDTO movie;
	private ScreenDTO screen;
	private LocalDateTime stTime;
	private LocalDateTime edTime;
}
