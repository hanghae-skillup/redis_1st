package com.hanghae.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 다른 모듈의 빈을 찾지 못해 scanBasePackages 설정
@SpringBootApplication(scanBasePackages = {"cinema-application", "cinema-adapter"})
public class AdapterApplication {
    public static void main(String[] args) {
        //서버 실행시 어뎁터로 실행
        SpringApplication.run(AdapterApplication.class, args);
    }
}
