package module.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.ticket.TicketDTO;
import dto.ticket.TicketReservationRequest;
import dto.ticket.TicketResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.service.ticket.TicketService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {

	private final TicketService ticketService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<TicketResponse>> getAllTicket(
		@NotNull @RequestParam Long showingId
	) {
		return ResponseEntity.ok(ticketService.getAllTicket(showingId));
	}

	@GetMapping(value = "/reserved")
	public ResponseEntity<List<TicketResponse>> getUserTicket(
		@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "사용자 아이디는 영문 및 숫자로 이루어져 있습니다.")
		@NotNull @RequestParam String username
	){
		return ResponseEntity.ok(ticketService.getUserTicket(username));
	}

	@PostMapping(value = "/reservation")
	public ResponseEntity<String> reservation(
		@RequestBody TicketReservationRequest request
	) {
		Long showingId = request.getShowingId();
		String username = request.getUsername();
		List<TicketDTO> ticketList = request.getTicketList();
		return ResponseEntity.ok(ticketService.reservationWithFunctional(showingId, username, ticketList));
	}
}
