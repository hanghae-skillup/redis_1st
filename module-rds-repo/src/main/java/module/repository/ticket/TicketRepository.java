package module.repository.ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import module.entity.Showing;
import module.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	List<Ticket> findAllByTicketIdIn(List<Long> ticketIdList);

	@Query(value = "select t, s1, s2, s3 "
		+ "from Ticket t "
		+ "join Seats s1 on t.seats.seatId = s1.seatId "
		+ "join Showing s2 on t.showing.showingId = s2.showingId "
		+ "join Screen s3 on s2.screen.screenId = s3.screenId "
		+ "where t.showing = :showing")
	List<Ticket> findAllByShowing(Showing showing);
}
