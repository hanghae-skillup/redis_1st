package com.movie.storage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
		"com.movie.domain", "com.movie.storage", "com.movie.redis"
})
class MovieStorageApplicationTests {

	@Test
	void contextLoads() {
	}

}
