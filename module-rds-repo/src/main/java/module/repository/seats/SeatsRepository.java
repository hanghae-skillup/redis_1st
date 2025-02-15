package module.repository.seats;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import module.entity.Screen;
import module.entity.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
	List<Seats> findAllByScreen(Screen screen);
	@Query(value = "select s "
		+ "from Seats s "
		+ "join Ticket t on s.seatId = t.seats.seatId "
		+ "where t.ticketId in :ticketIdList")
	List<Seats> findAllBySeatsInTicketIdList(List<Long> ticketIdList);
}
