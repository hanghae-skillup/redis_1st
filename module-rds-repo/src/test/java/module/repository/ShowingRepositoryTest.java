package module.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import dto.genre.GenreResponse;
import dto.movie.MovieResponse;
import dto.movie.MovieShowingResponse;
import module.repository.movie.MovieRepository;
import module.repository.showing.ShowingRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShowingRepositoryTest {

	private final ShowingRepository showingRepository;
	private final MovieRepository movieRepository;

	@Autowired
	public ShowingRepositoryTest(ShowingRepository showingRepository, MovieRepository movieRepository) {
		this.showingRepository = showingRepository;
		this.movieRepository = movieRepository;
	}

	@Test
	@DisplayName("1. 의존성 주입 테스트")
	public void injectionTest(){
		assertNotNull(showingRepository);
	}

	@Test
	@DisplayName("2. 모든영화의 상영정보인지 테스트")
	public void findAll(){
		// given
		List<MovieShowingResponse> movieShowingResponseList = showingRepository.getShowingByMovieTitleAndGenre(null, null);

		// when
		long numberOfMovie = movieRepository.count();

		// then
		assertEquals(numberOfMovie,movieShowingResponseList.size());
	}

	@Test
	@DisplayName("3. genre필터링 테스트")
	public void genreFiltering(){
		//given
		List<MovieShowingResponse> showingByMovieTitleAndGenre =
			showingRepository.getShowingByMovieTitleAndGenre(null,2L);

		//when
		List<Long> genreIdList = showingByMovieTitleAndGenre.stream()
			.map(MovieShowingResponse::getMovie)
			.map(MovieResponse::getGenre)
			.map(GenreResponse::getGenreId)
			.toList();

		//then
		genreIdList.stream().forEach(id-> assertTrue(id.equals(2L)));

		assertTrue(genreIdList.size() > 0);
	}

	@Test
	@DisplayName("4. 영화제목 필터링 테스트")
	public void titleFiltering() {
		//given
		List<MovieShowingResponse> showingByMovieTitleAndGenre =
			showingRepository.getShowingByMovieTitleAndGenre("말",null);

		//when
		List<String> titleList = showingByMovieTitleAndGenre.stream()
			.map(MovieShowingResponse::getMovie)
			.map(MovieResponse::getTitle)
			.toList();

		//then
		titleList.stream()
			.forEach(title-> assertTrue(title.contains("말")));

		assertTrue(titleList.size() > 0);
	}

	@Test
	@DisplayName("5. 영화제목 및 장르 아이디 필터링 테스트")
	public void titleAndGenreFiltering(){
		//given
		List<MovieShowingResponse> showingByMovieTitleAndGenre =
			showingRepository.getShowingByMovieTitleAndGenre("일",2L);

		//when
		List<MovieResponse> movieResponses = showingByMovieTitleAndGenre.stream()
			.map(MovieShowingResponse::getMovie)
			.toList();

		//then
		movieResponses.stream()
			.peek(movieResponse -> assertTrue(movieResponse.getTitle().contains("일")))
			.map(MovieResponse::getGenre)
			.forEach(genreResponse -> assertTrue(genreResponse.getGenreId().equals(2L)));

		assertTrue(movieResponses.size() > 0);
	}
}
