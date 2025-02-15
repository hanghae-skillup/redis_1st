package module.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import module.entity.Sales;
import module.entity.Ticket;
import dto.ticket.TicketStatus;
import module.entity.User;
import module.repository.sales.SalesRepository;
import module.repository.showing.ShowingRepository;
import module.repository.ticket.TicketRepository;
import module.repository.user.UserRepository;

@SpringBootTest
public class SalesRepositoryTest {

	@Autowired
	private SalesRepository salesRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShowingRepository showingRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@Test
	@DisplayName("의존성 주입 테스트")
	public void injectionTest(){
		Assertions.assertNotNull(salesRepository);
	}

	@Test
	@DisplayName("사용자의 구매정보 조회")
	public void showingFromSales(){
		//given
		// 유저 생성
		User user = userRepository.findByUsername("dbdb1114").get();
		// 티켓 조회
		Ticket ticket = ticketRepository.findById(1L).get();
		// 티켓 상태 변경
		ticket.setTicketStatus(TicketStatus.RESERVED);
		// 구매내역 저장
		salesRepository.save(Sales.builder()
			.price(9000)
			.ticket(ticket)
			.user(user)
			.createBy("system")
			.build());

		//when
		Integer showingId = salesRepository.countAllByUserAndShowing(user, ticket.getShowing());

		//then
		Assertions.assertEquals(ticket.getShowing().getShowingId(), showingId);
	}
}
