package dto.external;

import java.time.LocalDateTime;

import dto.internal.ScreenDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowingResponse {
	ScreenDTO screen;
	LocalDateTime stTime;
	LocalDateTime edTime;
}
