package com.lvonce.infra.persistent;

import com.lvonce.core.gateway.repository.IEndpointRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootTest
@ComponentScan("com.lvonce.infra.persistent")
public class AppTestConfig {

    @Bean
    JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
