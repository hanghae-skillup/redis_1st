package dto.seats;

import dto.screen.ScreenDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatsDTO {
	private Long seatId;
	private ScreenDTO screen;
	private String seatRow;
	private Integer seatNumber;
}
