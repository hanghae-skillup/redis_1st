package dto.internal;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MovieDTO {
	private Long id;
	private String title;
	private LocalDate openDay;
	private Integer runningTime;
	private String thumbnail;
	private GenreDTO genre;
	private RatingDTO rating;
	private String plot;
}
