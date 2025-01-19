package com.hanghae.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
1. 다른 모듈의 빈을 찾지 못하는 문제 해결을 위해 "@ComponentScan" 어노테이션 추가
2. JpaRepository를 찾지 못해 "@EnableJpaRepositories" 추가
3. Jpa Entity를 찾지 못해 "@EntityScan" 추가
4. application.properties 파일은 스프링부트를 실행시키는 "cinema-adapter" 모듈에 작성해야 적용됨
    * 타모듈 application.properties에 설정해도 적용 안됨
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hanghae.infrastructure.repository")
@EntityScan(basePackages = "com.hanghae.infrastructure.entity")
@ComponentScan(basePackages = {
        "com.hanghae.domain",
        "com.hanghae.application",
        "com.hanghae.infrastructure",
        "com.hanghae.adapter"
})
public class AdapterApplication {
    public static void main(String[] args) {
        //서버 실행시 어뎁터로 실행
        SpringApplication.run(AdapterApplication.class, args);
    }
}
