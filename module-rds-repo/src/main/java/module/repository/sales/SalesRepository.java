package module.repository.sales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import module.entity.Sales;
import module.entity.Showing;
import module.entity.Ticket;
import module.entity.User;

public interface SalesRepository extends JpaRepository<Sales, Long> {

	@Query(value = "select count(t.showing.showingId) "
		+ "from Sales s "
		+ "join Ticket t on s.ticket.showing = :showing "
		+ "join User u on s.user = :user ")
	Integer countAllByUserAndShowing(User user, Showing showing);

	@Query(value = "select t "
		+ "from Sales s "
		+ "join Ticket t on s.ticket.ticketId = t.ticketId "
		+ "where s.user = :user "
		+ "order by s.ticket.showing.showStTime ")
	List<Ticket> findAllByUserOOrderByShowingStTime(User user);
}
