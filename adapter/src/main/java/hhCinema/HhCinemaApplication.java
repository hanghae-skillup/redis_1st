package hhCinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"com.hanghae.adapter",       // 자신(어댑터) 패키지도 포함
				"com.hanghae.application",   // 서비스, 기타 비즈니스 로직 패키지
				"com.hanghae.domain",        // 엔티티, 리포지토리 패키지
				"com.hanghae.common"         // 공통
		}
)
@EntityScan(basePackages = "com.hanghae.domain.entity")
@EnableJpaRepositories(basePackages = "com.hanghae.domain.repository")
public class HhCinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhCinemaApplication.class, args);
	}

}
