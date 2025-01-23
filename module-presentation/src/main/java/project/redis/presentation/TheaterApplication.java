package project.redis.presentation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {
        "project.redis.application",
        "project.redis.presentation",
        "project.redis.domain",
        "project.redis.infrastructure"
})
public class TheaterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheaterApplication.class, args);
    }
}
