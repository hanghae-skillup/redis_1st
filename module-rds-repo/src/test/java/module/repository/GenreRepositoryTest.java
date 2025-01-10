package module.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import module.entity.Genre;
import module.util.PrivateGetSet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenreRepositoryTest {

	GenreRepository genreRepository;

	@Autowired
	public GenreRepositoryTest(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Test
	@DisplayName("1. InjectionTest")
	public void injectionTest() {
		assertNotNull(genreRepository);
	}

	@Test
	@DisplayName("2. 장르 조회 확인")
	public void findAllGenre() {
		//given
		List<String> dbList = List.of("로맨스", "드라마", "스릴러", "사극", "다큐멘터리", "SF", "가족", "뮤지컬", "액션");
		List<Genre> genreList = genreRepository.findAll();

		//when
		List<String> nameList = genreList.stream().map(genre -> PrivateGetSet.getValue(genre, "name", String.class))
			.toList();

		//then
		assertEquals(nameList.size(), 9);
		nameList.forEach(name -> {
			assertTrue(dbList.contains(name));
		});
	}
}
