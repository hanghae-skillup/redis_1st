package module.service.ticket;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dto.ticket.TicketDTO;
import dto.ticket.TicketResponse;
import dto.ticket.TicketStatus;
import exception.showing.ShowingNotFoundException;
import exception.ticket.InvalidAgeForMovieException;
import exception.ticket.InvalidSeatConditionException;
import exception.ticket.InvalidTicketException;
import exception.ticket.NotOnSaleTicketException;
import exception.ticket.TooManyReservationException;
import exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import module.entity.Movie;
import module.entity.Sales;
import module.entity.Seats;
import module.entity.Showing;
import module.entity.Ticket;
import module.entity.User;
import module.repository.sales.SalesRepository;
import module.repository.seats.SeatsRepository;
import module.repository.showing.ShowingRepository;
import module.repository.ticket.TicketRepository;
import module.repository.user.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

	private final ModelMapper modelMapper;
	private final ShowingRepository showingRepository;
	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;
	private final SalesRepository salesRepository;
	private final SeatsRepository seatsRepository;

	public List<TicketResponse> getAllTicket(Long showingId) {
		// 존재하는 상영정보인지 확인
		Optional<Showing> showingOptional = showingRepository.findById(showingId);
		if (!showingOptional.isPresent()) {
			throw new ShowingNotFoundException();
		}

		// 해당 상영 정보의 ticket 리스트 조회
		Showing showing = showingOptional.get();
		List<TicketResponse> ticketList = ticketRepository.findAllByShowing(showing)
			.stream().map(ticket -> modelMapper.map(ticket, TicketResponse.class))
			.toList();

		log.info("{}",ticketList);

		return ticketList;
	}

	public List<TicketResponse> getUserTicket(String username) {
		// 존재하는 유저인지 확인
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException();
		}

		// 해당 유저의 구매내역 확인
		User user = optionalUser.get();
		List<TicketResponse> ticketList = salesRepository.findAllByUserOOrderByShowingStTime(user)
			.stream().map(ticket -> modelMapper.map(ticket, TicketResponse.class)).toList();

		return ticketList;
	}

	public String reservation(Long showingId, String username, List<TicketDTO> ticketDtoList) {
		List<Ticket> ticketList = ticketRepository.findAllByTicketIdIn(
			ticketDtoList.stream().map(TicketDTO::getTicketId).toList());

		// 예외처리 [ 존재하지 않는 티켓 ]
		if (ticketList.size() != ticketDtoList.size())
			throw new InvalidTicketException();
		log.info("ticketId check pass");

		// 예외처리 [ 판매중이 아닌 티켓 ]
		ticketList.forEach(ticket->{
			if(ticket.getTicketStatus() != TicketStatus.ON_SALE){
				throw new NotOnSaleTicketException();
			}
		});

		// 비즈니스 [ 예매중 처리 ]
		ticketList.stream()
			.forEach(ticket -> ticket.setTicketStatus(TicketStatus.ON_RESERVING));

		// 예외처리 [ 존재하지 않는 사용자 ]
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException();
		}
		log.info("username check pass");

		// 예외처리 [ 티켓 구매 5건 초과 ]
		User user = optionalUser.get();
		Showing showing = showingRepository.findById(showingId).get();
		Integer userBoughtCnt = salesRepository.countAllByUserAndShowing(user, showing);
		if (userBoughtCnt + ticketList.size() > 5 || ticketList.size() > 5)
			throw new TooManyReservationException();
		log.info("user's sales ticket check pass");

		/**
		 // 영화가 이미 시작했을 경우
		 if(showing.getShowStTime().isAfter(LocalDateTime.now())){
		 throw new RuntimeException();
		 }
		 * */

		// 예외처리 [ 서로다른 행의 좌석 ]
		List<Seats> seatsInTicketList = seatsRepository.findAllBySeatsInTicketIdList(
			ticketList.stream().map(Ticket::getTicketId).toList());
		Integer numberOfSeatRow = seatsInTicketList.stream()
			.collect(Collectors.groupingBy(Seats::getSeatRow)).size();
		if (numberOfSeatRow > 1)
			throw new InvalidSeatConditionException();
		log.info("request ticket row seat check pass");

		// 예외처리 [ 부적절한 연령 ]
		Movie movie = showing.getMovie();
		int userAge = Period.between(user.getBirth(), LocalDate.now()).getYears();
		if (userAge < movie.getRating().getAge())
			throw new InvalidAgeForMovieException();
		log.info("movie rating check pass");

		// 최종연산 [ 결제 완료 처리 ]
		for (Ticket ticket : ticketList) {
			ticket.setTicketStatus(TicketStatus.RESERVED);
			Sales sales = Sales.builder().price(9000)
				.user(user).ticket(ticket)
				.createBy("system").build();
			salesRepository.save(sales);
		}

		return "success";
	}
}