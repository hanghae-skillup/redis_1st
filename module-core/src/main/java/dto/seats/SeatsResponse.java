package dto.seats;

import dto.screen.ScreenResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatsResponse {

	ScreenResponse screen;
	private String seatRow;
	private Integer seatNumber;

}

