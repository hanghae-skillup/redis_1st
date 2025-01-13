package module.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import module.entity.Screen;
import module.entity.Showing;

public interface ShowingRepository extends JpaRepository<Showing, Long> {

	@Query("SELECT s FROM Showing s "
		+ "JOIN FETCH s.movie m "
		+ "JOIN FETCH s.screen "
		+ "JOIN FETCH m.genre "
		+ "JOIN FETCH m.rating "
		+ "WHERE s.stTime >= :today "
		+ "ORDER BY m.openDay DESC, s.stTime asc")
	List<Showing> findShowingsByStTime(LocalDateTime today);

	@Query("SELECT s FROM Showing s " +
		"WHERE FUNCTION('DATE', s.stTime) = FUNCTION('DATE', :day) " +
		"AND s.screen = :screen " +
		"ORDER BY s.stTime DESC")
	List<Showing> findShowingsByStTimeLikeAndScreenIs(LocalDateTime day, Screen screen);
}
