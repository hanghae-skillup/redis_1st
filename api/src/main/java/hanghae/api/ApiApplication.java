package hanghae.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "hanghae.domain.entity")
@SpringBootApplication(
        scanBasePackages = {
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
