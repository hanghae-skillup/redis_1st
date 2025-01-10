package repository;

import static util.PrivateGetSet.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import entity.Showing;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShowingRepositoryTest {

	ShowingRepository showingRepository;

	@Autowired
	public ShowingRepositoryTest(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

	@Test
	@DisplayName("상영중인 영화 조회")
	public void selectShowing() {
		//given
		LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(6);
		LocalDateTime edDay = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.MAX);

		//when
		List<Showing> showings = showingRepository.findShowingByStTimeAfterAndEdTimeBeforeOrderByOpenDay(today, edDay);

		// //then
		showings.forEach(showing -> {
			Assertions.assertTrue(getValue(showing, "stTime", LocalDateTime.class).getDayOfMonth() <= LocalDate.now()
				.plusDays(2L)
				.getDayOfMonth());
		});
	}

}
