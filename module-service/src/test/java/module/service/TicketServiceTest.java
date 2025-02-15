package module.service;

import static dto.ticket.TicketStatus.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dto.ticket.TicketDTO;
import dto.ticket.TicketResponse;
import exception.BusinessError;
import exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import module.entity.Showing;
import module.entity.Ticket;
import module.entity.User;
import module.repository.sales.SalesRepository;
import module.repository.seats.SeatsRepository;
import module.repository.showing.ShowingRepository;
import module.repository.ticket.TicketRepository;
import module.repository.user.UserRepository;
import module.service.ticket.TicketService;

@Slf4j
@SpringBootTest
@Transactional
public class TicketServiceTest {

	private final TicketService ticketService;
	private final ShowingRepository showingRepository;
	private final SeatsRepository seatsRepository;
	private final ModelMapper modelMapper;

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private SalesRepository salesRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public TicketServiceTest(TicketService ticketService, ShowingRepository showingRepository,
		SeatsRepository seatsRepository, ModelMapper modelMapper) {
		this.ticketService = ticketService;
		this.showingRepository = showingRepository;
		this.seatsRepository = seatsRepository;
		this.modelMapper = modelMapper;
	}

	@Test
	@DisplayName("의존성 주입")
	public void injectionTest() {
		assertThat(ticketService).isNotNull();
	}

	@Test
	@DisplayName("[조회] - 기능")
	public void allTicketCount() {
		//given
		List<TicketResponse> allTicket = ticketService.getAllTicket(7759L);
		//when
		//then

		assertThat(allTicket.size()).isEqualTo(25);
	}

	@Test
	@DisplayName("[예매] - 기능")
	public void normalReservation() throws Exception {
		//given
		Long showingId = 7760L;
		String username = "dbdb1114";
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		List<TicketDTO> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0, 5);

		//when
		printMessage("ticketService.reservation start");
		ticketService.reservation(showingId, username, ticketList);
		printMessage("ticketService.reservation end");


		//then
		List<Ticket> tickets = ticketRepository.findAllByShowing(showing)
			.subList(0, 5);
		tickets.forEach(ticket -> assertThat(ticket.getTicketStatus())
			.isEqualTo(RESERVED));
	}

	@Test
	@DisplayName("[예매] 예외처리 - 존재하지 않는 유저")
	public void userNotFound() throws Exception {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		List<TicketDTO> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0, 5);

		//when
		String username = "invalidName";

		//then
		printMessage("ticketService.reservation start");
		Assertions.assertThatThrownBy(() ->
				ticketService.reservation(showingId, username, ticketList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.USER_NOT_FOUNT.getMessage());
		printMessage("ticketService.reservation end");

	}

	@Test
	@DisplayName("[예매] 예외처리 - 유효하지 않은 티켓")
	public void invalidTicketId() {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		List<TicketDTO> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0, 5);
		String username = "dbdb1114";

		//when
		ticketList.get(0).setTicketId(Long.MAX_VALUE);

		//then
		printMessage("ticketService.reservation start");
		assertThatThrownBy(() ->
				ticketService.reservation(showingId, username, ticketList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.RESERVATION_INVALID_TICKET.exception().getMessage());

		printMessage("ticketService.reservation end");
	}

	@Test
	@DisplayName("[예매] 예외처리 - 첫번째 구매시")
	public void exceededTicketAtFirst() {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		String username = "dbdb1114";

		//when
		List<TicketDTO> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0, 10);

		//then
		printMessage("ticketService.reservation start");
		assertThatThrownBy(() ->
				ticketService.reservation(showingId, username, ticketList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.RESERVATION_TOO_MANY_RESERVATION.getMessage());
		printMessage("ticketService.reservation end");
	}

	@Test
	@DisplayName("[예매] 예외처리 - 두번째 구매시")
	public void exceededTicketAtSecond() throws Exception {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		String username = "dbdb1114";
		List<TicketDTO> firstTicketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0, 3);
		ticketService.reservation(showingId, username, firstTicketList);

		//when
		List<TicketDTO> secondTicketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(3, 6);

		//then
		printMessage("ticketService.reservation start");
		assertThatThrownBy(() ->
				ticketService.reservation(showingId, username, secondTicketList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.RESERVATION_TOO_MANY_RESERVATION.getMessage());
		printMessage("ticketService.reservation end");
	}

	@Test
	@DisplayName("[예매] 예외처리 - 서르다른 행의 좌서")
	public void invalidRowNumberCombination() throws Exception {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		String username = "dbdb1114";

		//when
		List<TicketDTO> ticketDTOList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList();

		List<TicketDTO> mixedDTOList = new ArrayList<>();
		mixedDTOList.addAll(ticketDTOList.subList(0,2));
		mixedDTOList.addAll(ticketDTOList.subList(8,10));

		//then
		assertThatThrownBy(()->ticketService.reservation(showingId,username,mixedDTOList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.RESERVATION_INVALID_SEAT_CONDITION.getMessage());
	}

	@Test
	@DisplayName("[예매] 예외처리 - 관람연령 부적합")
	public void invalidUserAge() throws Exception {
		//given
		Long showingId = 7760L;
		Showing showing = Showing.builder()
			.showingId(7760L).build();
		String childName = "child";
		User user = User.builder()
			.username(childName)
			.password("child")
			.birth(LocalDate.of(2020, 1, 1))
			.createBy("system")
			.build();
		userRepository.save(user);

		//when
		List<TicketDTO> ticketDTOList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList().subList(0,5);

		//then
		assertThatThrownBy(()->ticketService.reservation(showingId,childName,ticketDTOList))
			.isInstanceOf(BusinessException.class)
			.hasMessage(BusinessError.RESERVATION_INVALID_AGE_FOR_MOVIE.getMessage());
	}

	private void printMessage(String message) {
		log.info("===================== {} =====================", message);
	}
}
