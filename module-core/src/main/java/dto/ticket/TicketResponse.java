package dto.ticket;

import dto.seats.SeatsResponse;
import dto.showing.ShowingResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

	Long ticketId;
	ShowingResponse showing;
	SeatsResponse seats;
	TicketStatus ticketStatus;

}
