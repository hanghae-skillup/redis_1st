package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShowingRepositoryTest {

	ShowingRepository showingRepository;

	@Autowired
	public ShowingRepositoryTest(ShowingRepository showingRepository) {
		this.showingRepository = showingRepository;
	}

}
