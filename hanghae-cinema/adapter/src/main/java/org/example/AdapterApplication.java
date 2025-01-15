package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example" )
public class AdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdapterApplication.class, args);
    }

}
