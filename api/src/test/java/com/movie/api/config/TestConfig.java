package com.movie.api.config;

import com.movie.application.service.ReservationService;
import com.movie.domain.repository.ReservationRepository;
import com.movie.domain.repository.ScheduleRepository;
import com.movie.domain.repository.SeatRepository;
import com.movie.domain.repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.redisson.spring.starter.RedissonAutoConfigurationV2;

import javax.sql.DataSource;
import java.util.Properties;

@TestConfiguration
@EnableAutoConfiguration(exclude = {
    RedisAutoConfiguration.class,
    RedisRepositoriesAutoConfiguration.class,
    RedissonAutoConfigurationV2.class
})
public class TestConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.movie.domain.entity");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(properties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    @Primary
    public ReservationService reservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            ScheduleRepository scheduleRepository,
            SeatRepository seatRepository) {
        return new ReservationService(reservationRepository, userRepository, scheduleRepository, seatRepository);
    }
} 