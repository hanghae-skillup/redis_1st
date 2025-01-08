package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainApplication {

    // 의존성 확인을 위한 코드 - 시작
    private final TestBean testBean;

    @Autowired
    public DomainApplication(TestBean testBean) {
        this.testBean = testBean;
    }

    @PostConstruct
    public void dependencyTest() {
        testBean.dependencyTest();
    }
    // 의존성 확인을 위한 코드 - 끝

    public static void main(String[] args) {
        SpringApplication.run(DomainApplication.class, args);
    }
}
