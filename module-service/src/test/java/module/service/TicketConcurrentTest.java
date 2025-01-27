package module.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import dto.ticket.TicketDTO;
import dto.ticket.TicketStatus;
import module.entity.Sales;
import module.entity.Showing;
import module.entity.Ticket;
import module.repository.sales.SalesRepository;
import module.repository.ticket.TicketRepository;
import module.service.ticket.TicketService;

@SpringBootTest
public class TicketConcurrentTest {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private SalesRepository salesRepository;

	@Test
	public void 티켓조회() {
		Ticket build = Ticket.builder().ticketId(35426L).build();
		Ticket build1 = Ticket.builder().ticketId(35427L).build();
		Ticket build2 = Ticket.builder().ticketId(35428L).build();
		List<Ticket> ticketList = List.of(build1,build2,build);
		List<Sales> allByTicketIn = salesRepository.findAllByTicketIn(ticketList);
		Assertions.assertThat(allByTicketIn.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("동시성 테스트 1000명의 유저 병렬 티켓 구매 시도")
	public void concurrentTest() throws InterruptedException {
		// given
		Long showingId = 8392L;
		Showing showing = Showing.builder()
			.showingId(showingId).build();
		List<TicketDTO> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> ticket.getTicketId())
			.map(id -> TicketDTO.builder().ticketId(id).build())
			.toList();

		// when
		CountDownLatch latch = new CountDownLatch(1000);

		IntStream.range(1, 1001).forEach(i -> {
			String username = "vuser" + i;
			TicketDTO ticketDTO = ticketList.get(i % ticketList.size());
			List<TicketDTO> ticketDTOList = List.of(ticketDTO);
			Thread.startVirtualThread(() -> {
				try {
					ticketService.reservation(showingId, username, ticketDTOList);
				} finally {
					latch.countDown(); // 스레드 작업 완료 시 감소
				}
			});
		});

		latch.await();

		// then
		// 모든 티켓 reserved 변경
		// 해당 티켓들의 sales 갯수 reserved ticket의 갯수와 동일
		List<Ticket> resultTicketList = ticketRepository.findAllByShowing(showing);
		resultTicketList.forEach(ticket -> System.out.println(ticket.getTicketStatus()));
		boolean isAllReserved = resultTicketList.stream().anyMatch(ticket -> ticket.getTicketStatus() != TicketStatus.RESERVED);
		Assertions.assertThat(isAllReserved).isFalse();
		List<Sales> salesResultList = salesRepository.findAllByTicketIn(resultTicketList);
		Assertions.assertThat(salesResultList.size()).isEqualTo(resultTicketList.size());
	}

}
