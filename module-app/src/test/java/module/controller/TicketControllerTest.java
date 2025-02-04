package module.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import dto.ticket.TicketResponse;

@SpringBootTest
@Rollback
public class TicketControllerTest {

	private final TicketController ticketController;

	@Autowired
	public TicketControllerTest(TicketController ticketController) {
		this.ticketController = ticketController;
	}

	@Test
	@DisplayName("[조회] - 기능")
	public void getAllTicketTest(){
		//given
		Long showingId = 7760L;

		//when
		ResponseEntity<List<TicketResponse>> allTicket = ticketController.getAllTicket(showingId);

		//then
		assertThat(allTicket.getStatusCode().value()).isEqualTo(200);
		assertThat(allTicket.getBody().size()).isEqualTo(25);
	}

}
