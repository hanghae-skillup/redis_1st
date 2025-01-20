package com.hh.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"com.hh.presentation","com.hh.application","com.hh.domain","com.hh.infrastructure"}
)
@EntityScan("com.hh.domain")
@EnableCaching
public class PresentationApplication {

  public static void main(String[] args) {
    SpringApplication.run(PresentationApplication.class, args);
  }

}
