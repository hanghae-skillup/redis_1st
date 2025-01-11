package com.project.adapter;

@SpringBootApplication(
        scanBasePackages = {
                "com.project.movie-adapter",       // 자신(어댑터) 패키지도 포함
                "com.project.movie-application",   // 서비스, 기타 비즈니스 로직 패키지
                "com.project.movie-domain"        // 엔티티, 리포지토리 패키지
        }
)
@EntityScan(basePackages = "com.project.movie-domain.entity")
@EnableJpaRepositories(basePackages = "com.project.movie-domain.repository")
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

}
