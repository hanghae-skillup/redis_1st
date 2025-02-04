package project.redis.infrastructure.util;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;


public abstract class TestContainerSupport {

    @Container
    public static final MySQLContainer<?> mysqlContainer
            = new MySQLContainer<>("mysql:9.1.0")
            .withDatabaseName("db")
            .withUsername("user")
            .withPassword("1234")
            .withCommand(
                    "--character-set-server=utf8mb4",
                    "--collation-server=utf8mb4_unicode_ci"
            );

    @Container
    public static final GenericContainer<?> redisContainer
            = new GenericContainer<>("redis")
            .withExposedPorts(6379);

    static {
        mysqlContainer.start();
        redisContainer.start();
    }

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        System.out.println("mysqlContainer = " + mysqlContainer.getJdbcUrl());
        registry.add("spring.datasource.url", ()-> mysqlContainer.getJdbcUrl() + "?useSSL=false&allowPublicKeyRetrieval=true");
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mysqlContainer::getDriverClassName);

        registry.add("redis.host", redisContainer::getHost);
        registry.add("redis.port", () -> redisContainer.getMappedPort(6379).toString());
    }
}
