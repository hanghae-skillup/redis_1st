package com.movie.storage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.movie.domain", "com.movie.storage", "com.movie.redis",
})
class MovieStorageApplicationTests {

	@Test
	void contextLoads() {
	}

}
