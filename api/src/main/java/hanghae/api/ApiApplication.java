package hanghae.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "hanghae.common",
                "hanghae.api",
                "hanghae.application",
                "hanghae.domain",
                "hanghae.infrastructure"
        }
)
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
