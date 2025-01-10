package repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Showing;

public interface ShowingRepository extends JpaRepository<Showing, Long> {

	@Query("SELECT s FROM Showing s "
		+ "JOIN FETCH s.movie m "
		+ "JOIN FETCH s.screen "
		+ "JOIN FETCH m.genre "
		+ "JOIN FETCH m.rating "
		+ "WHERE s.stTime >= :today AND s.stTime <= :edday")
	List<Showing> findShowingByStTimeAfterAndEdTimeBefore(LocalDateTime today, LocalDateTime edday);
}
