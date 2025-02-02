package dto.ticket;

import dto.seats.SeatsDTO;
import dto.showing.ShowingDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketDTO {

	Long ticketId;
	ShowingDTO showing;
	SeatsDTO seats;
	TicketStatus ticketStatus;

}
