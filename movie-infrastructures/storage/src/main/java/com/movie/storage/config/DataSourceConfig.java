package com.movie.storage.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/** hikrari config 추후에 설정 필요 */
//@Configuration
public class DataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "storage.datasource.core")
//    public HikariConfig coreHikariConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    public HikariDataSource coreDataSource(@Qualifier("coreHikariConfig") HikariConfig config) {
//        return new HikariDataSource(config);
//    }

}
