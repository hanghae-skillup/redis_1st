package repository;

import static org.junit.jupiter.api.Assertions.*;
import static util.PrivateGetSet.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import entity.Genre;

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

}
