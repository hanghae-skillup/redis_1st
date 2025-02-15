package dto.ticket;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketReservationRequest {
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "사용자 아이디는 영문및 숫자로 이루어져 있습니다.")
	String username;

	@NotNull
	Long showingId;

	@NotNull
	@Size(min = 1, max = 5, message = "예매 티켓의 경우 최소 1매, 최대 5매 입니다.")
	List<TicketDTO> ticketList;
}
