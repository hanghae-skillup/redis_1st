package module.repository;

import static org.junit.jupiter.api.Assertions.*;

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

import module.entity.Genre;
import module.entity.Movie;
import module.entity.Rating;
import module.entity.Showing;
import module.util.PrivateGetSet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShowingRepositoryTest {

	ShowingRepository showingRepository;

	@Autowired
	public ShowingRepositoryTest(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

	@Test
	@DisplayName("1. 상영중인 영화 조회")
	public void selectShowing() {
		//given
		LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(6);
		LocalDateTime edDay = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.MAX);

		//when
		List<Showing> showings = showingRepository.findShowingsByStTimeBetween(today, edDay);

		// //then
		showings.forEach(showing -> {
			Assertions.assertTrue(PrivateGetSet.getValue(showing, "stTime", LocalDateTime.class).getDayOfMonth() <= LocalDate.now()
				.plusDays(2L)
				.getDayOfMonth());
		});
	}

	@Test
	@DisplayName("2. 장르 및 영화등급 조회 테스트")
	public void showingConditionCheck() {
		// 영화별 장르 하나씩
		//given
		LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(6);
		LocalDateTime edDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		List<Showing> showings = showingRepository.findShowingsByStTimeBetween(today, edDay);

		//when
		List<Movie> movieList = showings.stream()
			.map(showing -> PrivateGetSet.getValue(showing,"movie", Movie.class))
			.toList();

		//then
		movieList.forEach(movie -> {
			Genre genre = PrivateGetSet.getValue(movie, "genre", Genre.class);
			String genreName = PrivateGetSet.getValue(genre, "name", String.class);
			Rating rating = PrivateGetSet.getValue(movie, "rating", Rating.class);
			String ratingName = PrivateGetSet.getValue(rating, "name", String.class);
			assertNotNull(genre);
			assertNotNull(genreName);
			assertNotNull(rating);
			assertNotNull(ratingName);
			assertFalse(genreName.isEmpty());
			assertFalse(ratingName.isEmpty());
		});
	}
}
